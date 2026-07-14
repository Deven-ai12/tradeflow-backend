package com.dev.tradeflow.exception;

public class InvalidVerificationTokenException extends RuntimeException{
	
	public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
