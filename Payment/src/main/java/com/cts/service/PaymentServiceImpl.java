package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.Booking;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.feignClient.BookingClient;
import com.cts.model.Payment;
import com.cts.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentRepository repository;

	@Autowired
	BookingClient bookingClient;
	Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Override
	public String addPayment(Payment payment) throws BookingIdNotFoundException {
		int bookingId = payment.getBookingId();
		
		Booking booked = bookingClient.viewBookingById(bookingId);
		//System.out.println(booked);
		if (booked == null)
			throw new BookingIdNotFoundException("Booking is not done ");
		else {
			Payment newPayment = repository.save(payment);
			if (newPayment != null) {
				booked.setStatus("Completed");
				booked.setBookingId(bookingId);
				bookingClient.updateBooking(booked);
				log.info("New payment is added");

				return "Payment successfull!!";
			} else
				return "Something went wrong!!!";
		}
	}

	@Override
	public Payment viewPaymentById(int paymentId) throws PaymentIdNotFoundException {
		Optional<Payment> optional = repository.findById(paymentId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new PaymentIdNotFoundException("Payment ID is invalid");
	}

	@Override
	public List<Payment> viewAllPayment() {
		return repository.findAll();
	}

}
