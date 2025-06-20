package com.cts.service; // Defines the package for the Payment Service interface

import java.util.List;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.model.Payment;

public interface PaymentService {

	// Adds a payment and throws an exception if the booking ID is not found
	public abstract String addPayment(Payment payment) throws BookingIdNotFoundException;

	// Retrieves payment details by payment ID, throws exception if ID is not found
	public abstract Payment viewPaymentById(int paymentId) throws PaymentIdNotFoundException;

	// Retrieves all payment records
	public abstract List<Payment> viewAllPayment();
	
	public abstract String updatePayment(Payment payment);
}
