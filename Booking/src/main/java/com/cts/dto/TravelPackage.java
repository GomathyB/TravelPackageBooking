package com.cts.dto; // Defines the package for the TravelPackage DTO

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@RequiredArgsConstructor // Generates a constructor for final fields and fields marked as @NonNull
public class TravelPackage {
    private int packageId; // Unique identifier for the travel package
    private String travelPackageName; // Name of the travel package
    private String category; // Category of the package (e.g., adventure, luxury, budget)
    private String place; // Destination of the travel package
    private String inclusions; // List of items included in the package (e.g., accommodation, meals)
    private int noOfDays; // Duration of the trip in days
    private int availability; // Available slots for booking
    private int price; // Cost of the package
}
