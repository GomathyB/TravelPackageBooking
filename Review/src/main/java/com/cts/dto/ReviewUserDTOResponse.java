package com.cts.dto; // Defines the package for the ReviewUserDTOResponse

import com.cts.model.Review;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and
		// toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
public class ReviewUserDTOResponse {
	private UserRoles user; // Stores user details related to the review
	private Review review; // Stores the review details associated with the user
}
