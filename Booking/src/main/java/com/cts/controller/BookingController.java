package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BookingPackageResponse;
import com.cts.dto.BookingUserResponse;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PackageNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Booking;
import com.cts.service.BookingServiceImpl;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	BookingServiceImpl service;

	@PostMapping("/addBooking")
	public String addBooking(@RequestBody Booking booking) throws PackageNotFoundException {
		return service.addBooking(booking);
	}

	@DeleteMapping("/deleteBooking/{bid}")
	public String deleteBookingById(@PathVariable("bid") int bookingId) {
		return service.deleteBookingById(bookingId);
	}

	@GetMapping("/viewBookingById/{bid}")
	public Booking viewBookingById(@PathVariable("bid") int bookingId) throws BookingIdNotFoundException {
		return service.viewBookingById(bookingId);
	}

	@GetMapping("viewAllBooking")
	public List<Booking> viewAllBooking() {
		return service.viewAllBooking();
	}
	
	@PutMapping("/updateBooking")
	public String  updateBooking(@RequestBody Booking booking)
	{
		return service.updateBooking(booking);
	}
	@GetMapping("/viewUserByBookingId/{bid}")
	public BookingUserResponse viewUserByBookingId(@PathVariable("bid")int bookingId) throws UserNotFoundException
	{
		return service.viewUserByBookingId(bookingId);
	}
	@GetMapping("/viewPackageByBookingId/{bid}")
	public BookingPackageResponse viewPackageByBookingId(@PathVariable("bid")int bookingId) throws UserNotFoundException, PackageNotFoundException
	{
		return service.viewPackageByBookingId(bookingId);
	}
	
}
