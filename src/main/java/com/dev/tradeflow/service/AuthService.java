package com.dev.tradeflow.service;

import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.RegisterResponse;

public interface AuthService {
	public RegisterResponse createUser(RegisterRequest registerRequest);
}
