package com.cts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Generates getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@RequiredArgsConstructor // Generates a no-argument constructor
@Entity // Marks this class as a JPA entity
public class TravelPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_seq")
	@SequenceGenerator(name = "package_seq", sequenceName = "package_sequence", initialValue = 1, allocationSize = 1)
	private int packageId; // Unique identifier for the travel package

	@NotBlank(message = "Travel Package name is mandatory")
	@Size(min = 2, max = 70, message = "Travel Package name must be between 3 and 70 characters")
	private String travelPackageName; // Name of the travel package

	@NotBlank(message = "Mention the category of the package")
	@Size(min = 2, max = 70, message = "Category must be between 3 and 70 characters")
	private String category; // Package category (e.g., adventure, leisure)

	@NotBlank(message = "Mention the place of the package")
	@Size(min = 2, max = 70, message = "Place must be between 3 and 70 characters")
	private String place; // Travel destination

	@NotBlank(message = "Mention the inclusions the package has")
	@Size(min = 2, max = 70, message = "Inclusions must be between 3 and 70 characters")
	private String inclusions; // Included services (transport, food, etc.)

	@Max(value = 10, message = "No of days should be at most 10")
	@Min(value = 1, message = "No of days should be at least 1")
	private int noOfDays; // Duration of the travel package

	@Max(value = 100, message = "The availability should be at most 100")
	@Min(value = 5, message = "The availability should be at least 5")
	private int availability; // Number of available slots

	@Max(value = 50000, message = "The price should be at most 50000")
	@Min(value = 1000, message = "The price should be at least 1000")
	private int price; // Cost of the travel package
	private String image;
	private int userId;
}
