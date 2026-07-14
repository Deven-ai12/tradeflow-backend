package com.dev.tradeflow.serviceimpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.RegisterResponse;
import com.dev.tradeflow.entity.User;
import com.dev.tradeflow.entity.VerificationToken;
import com.dev.tradeflow.exception.EmailAlreadyExistsException;
import com.dev.tradeflow.repository.UserRepository;
import com.dev.tradeflow.repository.VerificationTokenRepository;
import com.dev.tradeflow.service.AuthService;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, VerificationTokenRepository verificationTokenRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenRepository = verificationTokenRepository;
	}


	@Override
	@Transactional
	public RegisterResponse createUser(RegisterRequest registerRequest) {
		User user = mapToEntity(registerRequest);
		  if(emailExists(user)) {
	            throw new EmailAlreadyExistsException("User with email " +user.getEmail()
	                    + " already exists");
	        }
		  
		  User userResponse = userRepository.save(user);
		  
		  String token = UUID.randomUUID().toString();
		  
		  VerificationToken verificationToken = new VerificationToken();

		  verificationToken.setToken(token);
		  verificationToken.setUser(userResponse);
		  verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));

		  verificationTokenRepository.save(verificationToken);
		  
		 return mapToDto(userResponse);
	}
	
	
	private boolean emailExists(User user) {
        return userRepository.existsByEmail(user.getEmail());
    }

	private User mapToEntity(RegisterRequest registerRequest) {
		User user = new User();
		
		user.setFirstName(registerRequest.getFirstName());
		user.setLastName(registerRequest.getLastName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		
		return user;
		
	}
	
	private RegisterResponse mapToDto(User user) {
		RegisterResponse registerResponse = new RegisterResponse();
		
		registerResponse.setId(user.getId());
		registerResponse.setFirstName(user.getFirstName());
		registerResponse.setLastName(user.getLastName());
		registerResponse.setEmail(user.getEmail());
		registerResponse.setRole(user.getRole());
		registerResponse.setEnabled(user.getEnabled());
		registerResponse.setCreatedAt(user.getCreatedAt());
		registerResponse.setMessage("Registration successful. Please verify your email to activate your account.");
		
		return registerResponse;
		
	}
}
