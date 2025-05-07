package com.cts.service;

import java.util.List;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.model.Payment;

public interface PaymentService {
	public abstract String addPayment(Payment payment) throws BookingIdNotFoundException;
	
	public abstract Payment viewPaymentById(int paymentId) throws PaymentIdNotFoundException;
	
	public abstract List<Payment> viewAllPayment();
}
