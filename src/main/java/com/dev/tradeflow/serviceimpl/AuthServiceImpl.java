package com.dev.tradeflow.serviceimpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.dev.tradeflow.dto.request.LoginRequestDto;
import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.LoginResponseDto;
import com.dev.tradeflow.dto.response.ProfileResponse;
import com.dev.tradeflow.dto.response.RegisterResponse;
import com.dev.tradeflow.entity.User;
import com.dev.tradeflow.entity.VerificationToken;
import com.dev.tradeflow.exception.EmailAlreadyExistsException;
import com.dev.tradeflow.mapper.UserMapper;
import com.dev.tradeflow.repository.UserRepository;
import com.dev.tradeflow.repository.VerificationTokenRepository;
import com.dev.tradeflow.security.CustomUserDetails;
import com.dev.tradeflow.security.JwtService;
import com.dev.tradeflow.service.AuthService;
import com.dev.tradeflow.service.EmailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailService emailService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserMapper userMapper;
	
	
	public AuthServiceImpl(UserRepository userRepository,
			VerificationTokenRepository verificationTokenRepository, EmailService emailService,
			AuthenticationManager authenticationManager, JwtService jwtService, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userMapper = userMapper;
	}


	@Override
	@Transactional
	public RegisterResponse createUser(RegisterRequest registerRequest) {
		User user = userMapper.toEntity(registerRequest);
		if (emailExists(registerRequest.getEmail())) {
		    throw new EmailAlreadyExistsException(
		            "User with email " + registerRequest.getEmail() + " already exists.");
		}
		  
		  User userResponse = userRepository.save(user);
		  
		  String token = UUID.randomUUID().toString();
		  
		  VerificationToken verificationToken = new VerificationToken();

		  verificationToken.setToken(token);
		  verificationToken.setUser(userResponse);
		  verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));

		  verificationTokenRepository.save(verificationToken);
		  
		  emailService.sendVerificationEmail(userResponse, token);
		  
		 return userMapper.toResponse(userResponse);
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
	
	@Override
	public ProfileResponse getProfile() {

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    CustomUserDetails userDetails =
	            (CustomUserDetails) authentication.getPrincipal();

	    User user = userDetails.getUser();

	    ProfileResponse response = new ProfileResponse();

	    response.setId(user.getId());
	    response.setFirstName(user.getFirstName());
	    response.setLastName(user.getLastName());
	    response.setEmail(user.getEmail());
	    response.setRole(user.getRole());
	    response.setEnabled(user.getEnabled());
	    response.setCreatedAt(user.getCreatedAt());

	    return response;
	}
	
//	to check email exists or not 
	
	private boolean emailExists(String email) {
	    return userRepository.existsByEmail(email);
	}
	
	
}
