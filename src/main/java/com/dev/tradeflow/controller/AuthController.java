package com.dev.tradeflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.tradeflow.dto.request.LoginRequestDto;
import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.LoginResponseDto;
import com.dev.tradeflow.dto.response.RegisterResponse;
import com.dev.tradeflow.service.AuthService;
import com.dev.tradeflow.service.VerificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	private final VerificationService verificationService;

	public AuthController(AuthService authService, VerificationService verificationService) {
		this.authService = authService;
		this.verificationService = verificationService;
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
		RegisterResponse registerResponse = authService.createUser(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);

	}

	@GetMapping("/verify")
	public ResponseEntity<String> verifyEmail(@RequestParam String token) {

		verificationService.verifyEmail(token);

		return ResponseEntity.ok("Email verified successfully.");
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {

		return ResponseEntity.ok(authService.login(request));
	}

}
