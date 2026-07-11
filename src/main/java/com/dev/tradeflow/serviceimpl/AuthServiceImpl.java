package com.dev.tradeflow.serviceimpl;

import org.springframework.stereotype.Service;

import com.dev.tradeflow.dto.request.RegisterRequest;
import com.dev.tradeflow.dto.response.RegisterResponse;
import com.dev.tradeflow.entity.User;
import com.dev.tradeflow.exception.EmailAlreadyExistsException;
import com.dev.tradeflow.repository.UserRepository;
import com.dev.tradeflow.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	private UserRepository userRepository;
	
	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public RegisterResponse createUser(RegisterRequest registerRequest) {
		User user = mapToEntity(registerRequest);
		  if(emailExists(user)) {
	            throw new EmailAlreadyExistsException("User with email " +user.getEmail()
	                    + " already exists");
	        }
		 User userResponse = userRepository.save(user);
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
		user.setPassword(registerRequest.getPassword());
		
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
		registerResponse.setMessage("Registration successful.");
		
		return registerResponse;
		
	}
}
