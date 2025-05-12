package com.cts.model; // Defines the package for the Payment model

import jakarta.persistence.*; // JPA annotations for entity mapping
import jakarta.validation.constraints.*; // Validation annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and
		// toString methods
@RequiredArgsConstructor // Generates a constructor for final fields and fields marked as @NonNull
@AllArgsConstructor // Generates a constructor with all fields as arguments
@Entity // Marks this class as a JPA entity, meaning it will be mapped to a database
		// table
public class Payment {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq") // Auto-generates payment ID using
																					// sequence
	@SequenceGenerator(name = "payment_seq", sequenceName = "payment_sequence", initialValue = 1, allocationSize = 1) // Defines
																														// sequence
																														// properties
	@Id // Marks this field as the primary key
	private int paymentId; // Unique identifier for the payment

	private int bookingId; // ID of the booking associated with this payment

	@Min(value = 1000, message = "The minimum valid amount to pay is 1000") // Ensures minimum payment amount
	private int amount; // Payment amount

	@Pattern(regexp = "^(paid)$", message = "The status should be 'paid'") // Ensures valid payment status
	private String status; // Payment status (must be 'paid')

	@NotBlank(message = "The payment method field cannot be empty") // Ensures payment method is provided
	@Size(min = 6, message = "The payment method should be at least 6 characters") // Sets minimum length for payment
																					// method
	private String paymentMethod; // Payment method (e.g., Credit Card, PayPal, UPI)

	private int userId; // ID of the user who made the payment
}
