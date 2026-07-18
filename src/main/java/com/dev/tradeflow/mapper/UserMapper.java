package com.dev.tradeflow.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.RegisterResponse;
import com.dev.tradeflow.entity.User;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Convert RegisterRequest -> User Entity
     */
    public User toEntity(RegisterRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return user;
    }

    /**
     * Convert User Entity -> RegisterResponse
     */
    public RegisterResponse toResponse(User user) {

        RegisterResponse response = new RegisterResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setEnabled(user.getEnabled());
        response.setCreatedAt(user.getCreatedAt());

        response.setMessage(
                "Registration successful. Please verify your email to activate your account.");

        return response;
    }
}