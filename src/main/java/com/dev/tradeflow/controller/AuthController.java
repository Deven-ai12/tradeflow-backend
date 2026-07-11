package com.dev.tradeflow.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.RegisterResponse;
import com.dev.tradeflow.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
		RegisterResponse registerResponse = authService.createUser(registerRequest);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(registerResponse);
		
	}
}
