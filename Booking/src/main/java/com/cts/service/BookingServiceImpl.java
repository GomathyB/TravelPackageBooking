package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.BookingPackageResponse;
import com.cts.dto.BookingUserResponse;
import com.cts.dto.TravelPackage;
import com.cts.dto.UserRoles;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PackageNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.feignclient.BookingUserClient;
import com.cts.feignclient.TravelPackageClient;
import com.cts.model.Booking;
import com.cts.repository.BookingRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository repository;
	Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
	@Autowired
	TravelPackageClient packageClient;
	@Autowired
	BookingUserClient bookingUserClient;

	@Override
	public String addBooking(Booking booking) throws PackageNotFoundException {
		int noOfPeople = booking.getNoOfPeople();
		int packageId = booking.getPackageId();
		TravelPackage travelPackage = packageClient.viewPackageById(packageId);
		if (travelPackage != null) {
			int availability = travelPackage.getAvailability();
			if (availability - noOfPeople >= 0) {
				availability -= noOfPeople;
				travelPackage.setAvailability(availability);

				Booking newBooking = repository.save(booking);
				if (newBooking != null) {
					packageClient.updatePackage(travelPackage);
					log.info("New Booking is added");
					return "Booked package successfully!!";
				} else
					return "Something went wrong!!!";
			} else {
				return "Availability is not sufficient";
			}
		} else
			throw new PackageNotFoundException("Travel Package ID is invalid");
	}

	@Override
	public String deleteBookingById(int bookingId) {
		repository.deleteById(bookingId);
		log.info("A booking is deleted"); // Log message for deleting existing review
		return "Booking deleted successfully!!!";
	}

	@Override
	public Booking viewBookingById(int bookingId) throws BookingIdNotFoundException {
		log.info("Searching for booking details");
		Optional<Booking> optional = repository.findById(bookingId);
		if (optional.isPresent()) {
			log.info("Booking details found!!");
			return optional.get(); // Displaying the existing package
		} else {
			log.info("Booking details not found!! Booking ID is invalid");
			throw new BookingIdNotFoundException("Booking ID is invalid");
		}
	}

	@Override
	public List<Booking> viewAllBooking() {
		return repository.findAll();
	}

	@Override
	public String updateBooking(Booking booking) {
		Booking updateBooking = repository.save(booking);
		if (updateBooking != null)
			return "Booking updated successfully!!";
		else
			return "Something went wrong!!";
	}

	@Override
	public BookingUserResponse viewUserByBookingId(int bookingId) throws UserNotFoundException {
		Booking booking = repository.findById(bookingId).get();
		int userId = booking.getUserId();
		UserRoles user = bookingUserClient.viewUserById(userId);
		if (user != null) {
			BookingUserResponse response = new BookingUserResponse(booking, user);
			return response;
		} else {
			throw new UserNotFoundException("User ID not found");
		}
	}

	@Override
	public BookingPackageResponse viewPackageByBookingId(int bookingId)
			throws UserNotFoundException, PackageNotFoundException {
		Booking booking = repository.findById(bookingId).get();
		int packageId = booking.getPackageId();
		TravelPackage travelPackage = packageClient.viewPackageById(packageId);
		if (travelPackage != null) {
			BookingPackageResponse response = new BookingPackageResponse(booking, travelPackage);
			return response;
		} else {
			throw new PackageNotFoundException("Package ID is invalid");
		}
	}

	@Override
	public String cancelBooking(int bookingId) throws BookingIdNotFoundException, PackageNotFoundException {
		Booking booking=repository.findById(bookingId).get();
		if(booking!=null)
		{
			booking.setStatus("Cancelled");
			int noOfPeople=booking.getNoOfPeople();
			int packageId=booking.getPackageId();
			TravelPackage updatePackage=packageClient.viewPackageById(packageId);
			if(updatePackage!=null)
			{
				int availability=updatePackage.getAvailability();
				availability=availability+noOfPeople;
				updatePackage.setAvailability(availability);
				packageClient.updatePackage(updatePackage);
				updateBooking(booking);
				return "Booking Cancelled and availability increased";
			}
			else
			{
				throw new PackageNotFoundException("Package ID is invalid");
			}
			
		}
		else
		{
			throw new BookingIdNotFoundException("BookingId is invalid");
		}
	}
	
}
