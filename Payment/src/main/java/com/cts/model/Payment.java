package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
	@SequenceGenerator(name = "payment_seq", sequenceName = "payment_sequence", initialValue = 1, allocationSize = 1)
	@Id
	private int paymentId;
	private int bookingId;
	private int amount;
	private String status;
	private String paymentMethod;
	private int userId;
	
}
