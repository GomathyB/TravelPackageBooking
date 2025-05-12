package com.cts.exception; // Defines the package for custom exceptions

public class PaymentIdNotFoundException extends Exception { // Custom exception for handling missing payment IDs
	public PaymentIdNotFoundException(String message) { // Constructor to pass an error message
		super(message); // Calls the superclass (Exception) constructor with the provided message
	}
}
