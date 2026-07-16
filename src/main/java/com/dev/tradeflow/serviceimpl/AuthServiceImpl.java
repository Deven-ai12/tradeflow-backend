package com.dev.tradeflow.serviceimpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.tradeflow.dto.request.LoginRequestDto;
import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.LoginResponseDto;
import com.dev.tradeflow.dto.response.RegisterResponse;
import com.dev.tradeflow.entity.User;
import com.dev.tradeflow.entity.VerificationToken;
import com.dev.tradeflow.exception.EmailAlreadyExistsException;
import com.dev.tradeflow.repository.UserRepository;
import com.dev.tradeflow.repository.VerificationTokenRepository;
import com.dev.tradeflow.security.CustomUserDetails;
import com.dev.tradeflow.security.JwtService;
import com.dev.tradeflow.service.AuthService;
import com.dev.tradeflow.service.EmailService;
import org.springframework.security.core.Authentication;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailService emailService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, 
			VerificationTokenRepository verificationTokenRepository, EmailService emailService,
			AuthenticationManager authenticationManager, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		
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
		  
		  emailService.sendVerificationEmail(userResponse, token);
		  
		 return mapToDto(userResponse);
	}
	
	@Override
	public LoginResponseDto login(LoginRequestDto request) {

	    Authentication authentication =
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            request.getEmail(),
	                            request.getPassword()));

	    CustomUserDetails userDetails =
	            (CustomUserDetails) authentication.getPrincipal();

	    User user = userDetails.getUser();

	    String token = jwtService.generateToken(user.getEmail());

	    
	    		LoginResponseDto response = new LoginResponseDto();

	    response.setAccessToken(token);
	    response.setTokenType("Bearer");
	    response.setExpiresIn(86400000L);
	    response.setFirstName(user.getFirstName());
	    response.setLastName(user.getLastName());
	    response.setEmail(user.getEmail());
	    response.setRole(user.getRole().name());

	    return response;
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
