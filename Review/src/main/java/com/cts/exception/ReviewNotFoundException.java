package com.cts.exception;

public class ReviewNotFoundException extends Exception { // Review ID not found exception
	public ReviewNotFoundException(String message) {
		super(message);
	}

}
