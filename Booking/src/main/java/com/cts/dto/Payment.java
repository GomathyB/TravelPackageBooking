package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Payment {
	private int paymentId;
	private int bookingId; 
	private int amount;
	private String status; 
	private String paymentMethod; 
	private int userId; 

}
