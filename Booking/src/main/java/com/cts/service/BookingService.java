package com.cts.service;

import java.util.List;

import com.cts.dto.BookingPackageResponse;
import com.cts.dto.BookingUserResponse;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PackageNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Booking;

public interface BookingService {
	public abstract String addBooking(Booking booking)throws PackageNotFoundException;
	
	public abstract String deleteBookingById(int bookingId);
	
	public abstract Booking viewBookingById(int bookingId) throws BookingIdNotFoundException;
	
	public abstract List<Booking> viewAllBooking();
	
	public abstract String updateBooking(Booking booking);
	
	public abstract BookingUserResponse viewUserByBookingId(int bookingId) throws UserNotFoundException;
	
	public abstract BookingPackageResponse viewPackageByBookingId(int bookingId) throws UserNotFoundException, PackageNotFoundException;
	
}
