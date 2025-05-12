package com.cts.exception;

public class UserNotFoundException extends Exception { // User not found exception
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
