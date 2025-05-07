package com.cts.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Booking {
	private int bookingId;
	private int packageId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private int paymentId;
	private int userId;
}
