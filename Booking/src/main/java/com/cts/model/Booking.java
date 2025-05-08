package com.cts.model;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
	@SequenceGenerator(name = "booking_seq", sequenceName = "booking_sequence", initialValue = 1, allocationSize = 1)
	private int bookingId;
	private int packageId;
	private LocalDate startDate;
	private LocalDate endDate;
	@Pattern(regexp = "^(Pending|Completed)$", message = "Status should be either Pending or Completed")
	private String status;
	private int paymentId;
	@Min(value=1,message="The minimum number of people for booking should be atleast 1")
	private int noOfPeople;
	private int userId;
	@Transient	// Prevents the database from storing the isValidDate
	@JsonIgnore	// In postman the json will not be displayed
	@AssertTrue(message = "Start date must be before the end date")
    public boolean isvalidValidDates() {
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
	
}
