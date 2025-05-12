package com.cts.exception; // Defines the package for custom exceptions

public class BookingIdNotFoundException extends Exception { // Custom exception for handling missing booking IDs
	public BookingIdNotFoundException(String msg) { // Constructor to pass an error message
		super(msg); // Calls the superclass (Exception) constructor with the provided message
	}
}
