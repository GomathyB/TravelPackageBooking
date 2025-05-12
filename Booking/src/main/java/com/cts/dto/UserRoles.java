package com.cts.dto; // Defines the package for the UserRoles DTO

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@RequiredArgsConstructor // Generates a constructor for final fields or fields marked as @NonNull
@AllArgsConstructor // Generates a constructor with all fields as arguments
public class UserRoles {
    private int userId; // Unique identifier for the user
    private String name; // User's full name
    private String email; // User's email address
    private String password; // User's password (should be securely stored)
    private String role; // Defines the role of the user (e.g., ADMIN, CUSTOMER, TRAVEL_AGENT)
}
