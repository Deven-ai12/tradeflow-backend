package com.dev.tradeflow.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dev.tradeflow.dto.response.ExceptionResponseDto;
import com.dev.tradeflow.dto.response.ValidationExceptionResponseDto;
import com.dev.tradeflow.dto.response.VerificationTokenExpiredException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleEmailAlreadyExistsException(
    		EmailAlreadyExistsException ex, HttpServletRequest request) {

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponse);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> handleDMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDto exceptionResponse = new ValidationExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed",
                request.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
	
	@ExceptionHandler(InvalidVerificationTokenException.class)
	public ResponseEntity<ExceptionResponseDto> handleInvalidToken(
	        InvalidVerificationTokenException ex, HttpServletRequest request) {

	    ExceptionResponseDto response = new ExceptionResponseDto(
	            LocalDateTime.now(),
	            HttpStatus.BAD_REQUEST.value(),
	            HttpStatus.BAD_REQUEST.getReasonPhrase(),
	            ex.getMessage(),
	            request.getRequestURI()
	            );
	    

	    return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(VerificationTokenExpiredException.class)
	public ResponseEntity<ExceptionResponseDto> handleExpiredToken(
	        VerificationTokenExpiredException ex, HttpServletRequest request) {

	    ExceptionResponseDto response = new ExceptionResponseDto(
	    		LocalDateTime.now(),
	            HttpStatus.BAD_REQUEST.value(),
	            HttpStatus.BAD_REQUEST.getReasonPhrase(),
	            ex.getMessage(),
	            request.getRequestURI()
	            );

	    return ResponseEntity.badRequest().body(response);
	}

}
