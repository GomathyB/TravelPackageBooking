package com.cts.dto; // Defines the package for the UserRoles DTO

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and
		// toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@RequiredArgsConstructor // Generates a constructor for final fields and fields marked as @NonNull
public class UserRoles {
	private int userId; // Unique identifier for the user
	private String name; // Stores the user's name
	private String email; // Stores the user's email address
	private String password; // Stores the user's password
	private String role; // Stores the user's role (only valid listed roles should be used)
}
