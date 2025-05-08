package com.cts.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Booking {
	private int bookingId;
	private int packageId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private int paymentId;
	private int userId;
	private int noOfPeople;
}
