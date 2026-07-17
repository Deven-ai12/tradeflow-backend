package com.dev.tradeflow.service;

import com.dev.tradeflow.dto.request.LoginRequestDto;
import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.LoginResponseDto;
import com.dev.tradeflow.dto.response.ProfileResponse;
import com.dev.tradeflow.dto.response.RegisterResponse;

public interface AuthService {
	public RegisterResponse createUser(RegisterRequest registerRequest);
	public LoginResponseDto login(LoginRequestDto loginRequestDto);
	ProfileResponse getProfile();
}
