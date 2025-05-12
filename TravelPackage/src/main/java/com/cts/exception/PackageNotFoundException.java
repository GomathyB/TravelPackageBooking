package com.cts.exception;

public class PackageNotFoundException extends Exception { // PackageNotFoundException exception for handling the package
															// id inavalid
	public PackageNotFoundException(String message) {
		super(message);
	}
}
