package com.cts.feignclient; // Defines the package for the Feign Client

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.dto.Booking;

@FeignClient(name = "BOOKING", path = "/booking") // Declares this interface as a Feign Client for the "BOOKING"
													// microservice
public interface BookingClient {

	@GetMapping("/viewBookingById/{bid}") // Fetches a booking by its ID (GET request)
	public Booking viewBookingById(@PathVariable("bid") int bookingId) throws BookingIdNotFoundException;

	@PutMapping("/updateBooking") // Updates booking details (PUT request)
	public String updateBooking(Booking booking);
}
