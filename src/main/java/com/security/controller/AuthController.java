package com.security.controller;

import com.security.entity.User;
import com.security.service.AuthService;
import com.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register-user")
    public String register(@RequestBody User user){
        userService.register(user);
        return "User Registered";
    }

    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody User user){
        userService.register(user);
        return "Admin Registered";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        return authService.login(
                user.getUsername(),
                user.getPassword());
    }

    @PostMapping("/refresh")
    public Map<String, String> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        return authService.refreshToken(refreshToken);
    }



}
