package com.cts.dto;
import java.util.List;

import com.cts.model.TravelPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@NoArgsConstructor // Generates a no-argument constructor
public class ReviewPackageResponseDTO {
    private List<Review> reviews; // Stores list of reviews related to a package
    private TravelPackage travelPackage; // Stores the travel package details
}
