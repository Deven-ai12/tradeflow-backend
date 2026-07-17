package com.dev.tradeflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.tradeflow.dto.response.ProfileResponse;
import com.dev.tradeflow.service.AuthService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getProfile() {
        return ResponseEntity.ok(authService.getProfile());
    }
}