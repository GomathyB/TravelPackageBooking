package com.cts.service; // Defines the package for the Booking service implementation

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


@Service // Marks this class as a service component for business logic
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository repository; // Repository for Booking entity operations

	Logger log = LoggerFactory.getLogger(BookingServiceImpl.class); // Logger for tracking operations

	@Autowired
	TravelPackageClient packageClient; // Feign Client for fetching TravelPackage details

	@Autowired
	BookingUserClient bookingUserClient; // Feign Client for fetching User details

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
				} else {
					return "Something went wrong!!!";
				}
			} else {
				return "Availability is not sufficient";
			}
		} else {
			throw new PackageNotFoundException("Travel Package ID is invalid");
		}
	}

	@Override
	public String deleteBookingById(int bookingId) {
		repository.deleteById(bookingId);
		log.info("A booking is deleted");
		return "Booking deleted successfully!!!";
	}

	@Override
	public Booking viewBookingById(int bookingId) throws BookingIdNotFoundException {
		log.info("Searching for booking details");
		Optional<Booking> optional = repository.findById(bookingId);
		if (optional.isPresent()) {
			log.info("Booking details found!!");
			return optional.get();
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
		return updateBooking != null ? "Booking updated successfully!!" : "Something went wrong!!";
	}

	@Override
	public BookingUserResponse viewUserByBookingId(int bookingId) throws UserNotFoundException {
		Booking booking = repository.findById(bookingId).get();
		int userId = booking.getUserId();
		UserRoles user = bookingUserClient.viewUserById(userId);

		if (user != null) {
			return new BookingUserResponse(booking, user);
		} else {
			throw new UserNotFoundException("User ID not found");
		}
	}

	@Override
	public BookingPackageResponse viewPackageByBookingId(int bookingId) throws PackageNotFoundException {
		Booking booking = repository.findById(bookingId).get();
		int packageId = booking.getPackageId();
		TravelPackage travelPackage = packageClient.viewPackageById(packageId);

		if (travelPackage != null) {
			return new BookingPackageResponse(booking, travelPackage);
		} else {
			throw new PackageNotFoundException("Package ID is invalid");
		}
	}

	@Override
	public String cancelBooking(int bookingId) throws BookingIdNotFoundException, PackageNotFoundException {
		Booking booking = repository.findById(bookingId)
				.orElseThrow(() -> new BookingIdNotFoundException("Booking ID is invalid"));

		booking.setStatus("Cancelled");
		int noOfPeople = booking.getNoOfPeople();
		int packageId = booking.getPackageId();
		TravelPackage updatePackage = packageClient.viewPackageById(packageId);

		if (updatePackage != null) {
			int availability = updatePackage.getAvailability();
			availability += noOfPeople;
			updatePackage.setAvailability(availability);
			packageClient.updatePackage(updatePackage);
			updateBooking(booking);
			return "Booking Cancelled and availability increased";
		} else {
			throw new PackageNotFoundException("Package ID is invalid");
		}
	}
}
