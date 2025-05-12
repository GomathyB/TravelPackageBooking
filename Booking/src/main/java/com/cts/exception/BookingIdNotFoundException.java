package com.cts.exception; // Defines the package for custom exceptions

public class BookingIdNotFoundException extends Exception { // Custom exception for handling missing booking IDs
    public BookingIdNotFoundException(String message) { // Constructor to pass an error message
        super(message); // Calls the superclass (Exception) constructor with the message
    }
}
