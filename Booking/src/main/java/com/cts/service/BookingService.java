package com.cts.service; // Defines the package for the Booking Service interface

import java.util.List;

import com.cts.dto.BookingPackageResponse;
import com.cts.dto.BookingUserResponse;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PackageNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Booking;

public interface BookingService {

	// Adds a new booking and throws an exception if the package is not found
	public abstract String addBooking(Booking booking) throws PackageNotFoundException;

	// Deletes a booking by its ID
	public abstract String deleteBookingById(int bookingId);

	// Retrieves booking details by ID, throws exception if ID is not found
	public abstract Booking viewBookingById(int bookingId) throws BookingIdNotFoundException;

	// Retrieves all booking records
	public abstract List<Booking> viewAllBooking();

	// Updates booking details
	public abstract String updateBooking(Booking booking);

	// Fetches user details associated with a booking, throws exception if user is
	// not found
	public abstract BookingUserResponse viewUserByBookingId(int bookingId) throws UserNotFoundException;

	// Fetches package details associated with a booking, throws exceptions if user
	// or package is not found
	public abstract BookingPackageResponse viewPackageByBookingId(int bookingId)
			throws UserNotFoundException, PackageNotFoundException;

	// Cancels a booking by its ID, throws exceptions if booking or package is not
	// found
	public abstract String cancelBooking(int bookingId) throws BookingIdNotFoundException, PackageNotFoundException;
}
