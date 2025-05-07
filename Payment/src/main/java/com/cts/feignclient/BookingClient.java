package com.cts.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cts.exception.BookingIdNotFoundException;
import com.cts.dto.Booking;
@FeignClient(name="BOOKING",path="/booking")
public interface BookingClient {
	
	
	@GetMapping("/viewBookingById/{bid}")
	public Booking viewBookingById(@PathVariable("bid") int bookingId) throws BookingIdNotFoundException;
	@PutMapping("/updateBooking")
	public String  updateBooking(Booking booking);
}
