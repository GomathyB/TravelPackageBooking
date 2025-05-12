package com.cts.exception; // Defines the package for custom exceptions

public class UserNotFoundException extends Exception { // Custom exception for handling user-related errors
	public UserNotFoundException(String msg) { // Constructor to pass an error message
		super(msg); // Calls the superclass (Exception) constructor with the provided message
	}
}
