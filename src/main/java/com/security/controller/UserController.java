package com.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public String profile(Authentication auth) {
        return "Welcome " + auth.getName();
    }

    @GetMapping("dashboard1")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminCheck(){
        return "admin dashboard";
    }

    @GetMapping("dashboard2")
    @PreAuthorize("hasRole('USER')")
    public String userCheck(){
        return "user dashboard";
    }


    @PreAuthorize("#username == authentication.name")
    @GetMapping("/profile/{username}")
    public String getProfile(@PathVariable String username) {
        return "User profile";
    }


}
