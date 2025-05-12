package com.cts.model; // Defines the package for the Review model

import java.time.LocalDateTime;

import jakarta.persistence.*; // JPA annotations for entity mapping
import jakarta.validation.constraints.*; // Validation annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and
		// toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@RequiredArgsConstructor // Generates a constructor for final fields and fields marked as @NonNull
@Entity // Marks this class as a JPA entity, meaning it will be mapped to a database
		// table
public class Review {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq") // Auto-generates review ID using
																					// sequence
	@SequenceGenerator(name = "review_seq", sequenceName = "review_sequence", initialValue = 1, allocationSize = 1) // Defines
																													// sequence
																													// properties
	@Id // Marks this field as the primary key
	private int reviewId; // Unique identifier for the review

	private int packageId; // ID of the travel package being reviewed

	@Max(value = 5, message = "The maximum rating should be 5") // Ensures maximum rating is 5
	@Min(value = 1, message = "The minimum rating should be 1") // Ensures minimum rating is 1
	private int rating; // Rating given for the travel package

	@NotBlank(message = "Please comment on your experience about the travel") // Ensures review comments aren't blank
	@Size(min = 2, max = 70, message = "Comment must be between 2 and 70 characters") // Restricts comment length for
																						// consistency
	private String comment; // Review comment for the travel package

	@Column(name = "timestamp", updatable = false) // Stores review timestamp, prevents updates
	private LocalDateTime timestamp; // Timestamp for when the review was created

	private int userId; // ID of the user who submitted the review

	@PrePersist // Runs this method before persisting a new entity in the database
	protected void onCreate() {
		this.timestamp = LocalDateTime.now(); // Automatically sets timestamp to current date and time
	}
}
