package com.dev.tradeflow.service;

import com.dev.tradeflow.entity.User;

public interface EmailService {

	void sendVerificationEmail(User user, String token);
}
