package com.cts.service;

import java.util.List;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.model.Booking;

public interface BookingService {
	public abstract String addBooking(Booking booking);
	
	public abstract String deleteBookingById(int bookingId);
	
	public abstract Booking viewBookingById(int bookingId) throws BookingIdNotFoundException;
	
	public abstract List<Booking> viewAllBooking();
	
	public abstract String updateBooking(Booking booking);
	
}
