package com.dev.tradeflow.exception;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends RuntimeException{
	
	public EmailAlreadyExistsException(String message) {
		super(message);
	}

}
