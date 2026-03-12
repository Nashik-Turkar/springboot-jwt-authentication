package com.security.service;

import com.security.entity.Role;
import com.security.entity.User;
import com.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User request){

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRole(Role.USER);
        request.setEnabled(true);
        return userRepository.save(request);
    }

    public User registerAdmin(User request){

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRole(Role.ADMIN);
        request.setEnabled(true);
        return userRepository.save(request);
    }
}
