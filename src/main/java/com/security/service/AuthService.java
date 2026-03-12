package com.security.service;

import com.security.entity.Token;
import com.security.entity.User;
import com.security.repository.TokenRepository;
import com.security.repository.UserRepository;
import com.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Value("${jwt.access-token-expiration}")
    private long accessExp;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshExp;

    public Map<String, String> login(String username, String password) {

        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username, password));

        User user = userRepository.findByUsername(username).orElseThrow();

        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        // convert milliseconds → LocalDateTime
        LocalDateTime accessExpiry = LocalDateTime.now().plusSeconds(accessExp / 1000);
        LocalDateTime refreshExpiry = LocalDateTime.now().plusSeconds(refreshExp / 1000);

        // revoke old tokens
        tokenService.revokeAllUserTokens(user);

        // save new token
        tokenService.saveToken(
                user,
                accessToken,
                refreshToken,
                accessExpiry,
                refreshExpiry
        );

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    public Map<String, String> refreshToken(String refreshToken) {

        Optional<Token> tokenOpt =
                tokenRepository.findByRefreshToken(refreshToken);

        if (tokenOpt.isEmpty()) {
            throw new RuntimeException("Invalid Refresh Token");
        }

        Token token = tokenOpt.get();

        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh Token Expired");
        }

        String username = jwtUtil.extractUsername(refreshToken);

        String newAccessToken = jwtUtil.generateAccessToken(username);

        token.setAccessToken(newAccessToken);

        token.setAccessTokenExpiry(
                LocalDateTime.now().plusSeconds(accessExp / 1000)
        );

        tokenRepository.save(token);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }
}