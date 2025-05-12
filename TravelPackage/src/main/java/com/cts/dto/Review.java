package com.cts.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@NoArgsConstructor // Generates a no-argument constructor
public class Review {
	private int reviewId; // Unique identifier for the review
	private int packageId; // ID of the associated travel package
	private int rating; // User rating for the package
	private String comment; // User review comments
	private LocalDateTime timestamp; // Timestamp when the review was submitted
	private int userId; // ID of the user who submitted the review
}
