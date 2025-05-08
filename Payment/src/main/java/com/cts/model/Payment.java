package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@Min(value=1000, message="The minimum valid amount to pay is 1000")
	private int amount;
	@Pattern(regexp = "^(paid)$", message = "The staus should be paid")
	private String status;
	@NotNull(message="The method is not filled")
	@Size(min = 6, message = "The payment method should be atleast 6 characters")
	private String paymentMethod;
	private int userId;
	
}
