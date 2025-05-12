package com.cts.model; // Defines the package for the Booking model

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*; // JPA annotations for entity mapping
import jakarta.validation.constraints.*; // Validation annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Generates a constructor with all fields as arguments
@RequiredArgsConstructor // Generates a constructor for final fields and fields marked as @NonNull
@Entity // Marks this class as a JPA entity, meaning it will be mapped to a database table
public class Booking {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq") // Auto-generates booking ID using sequence
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_sequence", initialValue = 1, allocationSize = 1) // Defines sequence properties
    private int bookingId; // Unique identifier for a booking

    private int packageId; // Stores the travel package ID
    private LocalDate startDate; // Start date of the booking
    private LocalDate endDate; // End date of the booking

    @Pattern(regexp = "^(Pending|Completed)$", message = "Status should be either Pending or Completed") // Ensures valid booking status
    private String status; // Booking status (Pending or Completed)

    private int paymentId; // Stores the payment ID associated with the booking

    @Min(value = 1, message = "The minimum number of people for booking should be at least 1") // Validation for minimum people required
    private int noOfPeople; // Number of people included in the booking

    private int userId; // Stores the user ID related to the booking

    @Transient // Prevents this field from being stored in the database
    @JsonIgnore // Ensures this field is ignored in JSON responses (e.g., in Postman)
    @AssertTrue(message = "Start date must be before the end date") // Validation to ensure the start date comes before the end date
    public boolean isValidValidDates() {
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
}
