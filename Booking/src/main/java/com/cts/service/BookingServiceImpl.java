package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.model.Booking;
import com.cts.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository repository;
	Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
	@Override
	public String addBooking(Booking booking) {
		Booking newBooking = repository.save(booking);
		if (newBooking != null) {
			log.info("New Booking is added");
			return "Booked package successfully!!";
		} else
			return "Something went wrong!!!";
	}

	@Override
	public String deleteBookingById(int bookingId) {
		repository.deleteById(bookingId);
		log.info("A booking is deleted"); // Log message for deleting existing review
		return "Booking deleted successfully!!!";
	}

	@Override
	public Booking viewBookingById(int bookingId) throws BookingIdNotFoundException {
		Optional<Booking> optional = repository.findById(bookingId);
		if (optional.isPresent())
			return optional.get(); // Displaying the existing package
		else
			throw new BookingIdNotFoundException("Booking ID is invalid");
	}

	@Override
	public List<Booking> viewAllBooking() {
		return repository.findAll();
	}

	@Override
	public String updateBooking(Booking booking) {
		repository.save(booking);
		return "Booking updated successfully!!";
	}

}
