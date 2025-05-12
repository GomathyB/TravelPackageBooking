package com.cts.exception; // Defines the package for custom exceptions

public class PackageNotFoundException extends Exception { // Custom exception for handling package-related errors
    public PackageNotFoundException(String msg) { // Constructor to pass an error message
        super(msg); // Calls the superclass (Exception) constructor with the provided message
    }
}
