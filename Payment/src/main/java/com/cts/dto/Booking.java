package com.cts.dto; // Defines the package for the Booking DTO

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and
		// toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
public class Booking {
	private int bookingId; // Unique identifier for the booking
	private int packageId; // ID of the travel package associated with this booking
	private LocalDate startDate; // Start date of the booking
	private LocalDate endDate; // End date of the booking
	private String status; // Booking status (e.g., Pending, Completed, Cancelled)
	private int paymentId; // ID of the payment linked to the booking
	private int userId; // ID of the user who made the booking
	private int noOfPeople; // Number of people included in the booking
}
