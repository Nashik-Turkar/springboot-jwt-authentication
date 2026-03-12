package com.security.service;

import com.security.entity.Token;
import com.security.entity.User;
import com.security.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void revokeAllUserTokens(User user) {
        List<Token> tokens =
                tokenRepository.findByUserAndRevokedFalse(user);

        tokens.forEach(token -> token.setRevoked(true));
        tokenRepository.saveAll(tokens);
    }

    public void saveToken(User user,
                          String access,
                          String refresh,
                          LocalDateTime accessExp,
                          LocalDateTime refreshExp) {

        Token token = Token.builder()
                .user(user)
                .accessToken(access)
                .refreshToken(refresh)
                .accessTokenExpiry(accessExp)
                .refreshTokenExpiry(refreshExp)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();

        tokenRepository.save(token);
    }

}
