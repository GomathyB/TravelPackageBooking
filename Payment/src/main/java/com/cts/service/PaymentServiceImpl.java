package com.cts.service; // Defines the package for the Payment service implementation

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.Booking;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.feignclient.BookingClient;
import com.cts.model.Payment;
import com.cts.repository.PaymentRepository;

@Service // Marks this class as a service component for business logic
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository repository; // Injects PaymentRepository for database operations

	@Autowired
	BookingClient bookingClient; // Feign Client to interact with the Booking microservice

	Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class); // Logger for tracking operations

	@Override
	public String addPayment(Payment payment) throws BookingIdNotFoundException {
		int bookingId = payment.getBookingId();

		Booking booked = bookingClient.viewBookingById(bookingId); // Fetch booking details
		if (booked == null) {
			throw new BookingIdNotFoundException("Booking is not done");
		} else {
			Payment newPayment = repository.save(payment); // Saves payment record
			if (newPayment != null) {
				booked.setStatus("Completed"); // Updates booking status
				bookingClient.updateBooking(booked); // Updates booking details
				log.info("New payment is added");

				return "Payment successful!!";
			} else {
				return "Something went wrong!!!";
			}
		}
	}

	@Override
	public Payment viewPaymentById(int paymentId) throws PaymentIdNotFoundException {
		log.info("Searching for payment details");
		Optional<Payment> optional = repository.findById(paymentId); // Searches payment by ID
		if (optional.isPresent()) {
			log.info("Payment details found!!");
			return optional.get();
		} else {
			log.info("Payment details not found!! Payment ID invalid");
			throw new PaymentIdNotFoundException("Payment ID is invalid");
		}
	}

	@Override
	public List<Payment> viewAllPayment() {
		return repository.findAll(); // Fetches all payment records
	}
}
