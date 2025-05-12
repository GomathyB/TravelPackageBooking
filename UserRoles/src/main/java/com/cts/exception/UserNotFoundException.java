package com.cts.exception;

public class UserNotFoundException extends Exception { // Custom exception when the user id is invalid
	public UserNotFoundException(String message) {
		super(message);
	}
}
