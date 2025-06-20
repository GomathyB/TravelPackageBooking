package com.cts.service; // Defines the package for the Booking service implementation

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.dto.BookingPackageResponse;
import com.cts.dto.BookingUserResponse;
import com.cts.dto.Payment;
import com.cts.dto.TravelPackage;
import com.cts.dto.UserRoles;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PackageNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.feignclient.BookingUserClient;
import com.cts.feignclient.MailClient;
import com.cts.feignclient.PaymentClient;
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
	
	@Autowired
	PaymentClient paymentClient;
	
	@Autowired
	MailClient mailClient;

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
					String msg="Successfully booked a package for "+travelPackage.getPlace()+" for "+booking.getNoOfPeople()+" people. Enjoy your trip!!!";
					mailClient.sendEmail("gomathybaskaran04@gmail.com", "Pack2Go Travel Pacakage Booking", msg);
					String bookedMsg="Booked package successfully!! "+newBooking.getBookingId();
					return bookedMsg;
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
	@Transactional
	public String cancelBookingById(int bookingId) throws BookingIdNotFoundException, PackageNotFoundException, PaymentIdNotFoundException {
		Optional<Booking> booking=repository.findById(bookingId);
		if(booking.isPresent()) {
			Booking booked=booking.get();
			if(booked.getStatus().equals("Completed"))
			{
//				Changing the payment status to cancelled
				Payment payment=paymentClient.viewPaymentById(booked.getPaymentId());
				payment.setStatus("Cancelled");
				System.out.println("Payment Object"+payment.getPaymentId()+" ,"+payment.getBookingId());
				paymentClient.updatePayment(payment);	//------
				
				//Increasing the availability in the package
				int no_of_people = booked.getNoOfPeople();
				int packageId=booked.getPackageId();
				TravelPackage travelPackage=packageClient.viewPackageById(packageId);
				int availability=travelPackage.getAvailability();
				availability+=no_of_people;
				travelPackage.setAvailability(availability);
				packageClient.updatePackage(travelPackage);
				booked.setStatus("Cancelled");//---------
				repository.save(booked);
				log.info("A booking is updated");
				return "Booking and payment cancelled";
			}
			else if(booked.getStatus().equals("Pending"))
			{
				//Increase the availability
				int no_of_people=booked.getNoOfPeople();
				int packageId=booked.getPackageId();
				TravelPackage travelPackage=packageClient.viewPackageById(packageId);
				int availability=travelPackage.getAvailability();
				availability+=no_of_people;
				travelPackage.setAvailability(availability);
				packageClient.updatePackage(travelPackage);
				booked.setStatus("Cancelled");// ------------
				repository.save(booked);
				//log.info("A booking is updated");
//				return "Booking deleted successfully!!!";
				return "Booking cancelled";
			}	
		}
		else
		{
			throw new BookingIdNotFoundException("Booking ID is invalid");
		}
		return "Cancelled";
		
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

	
}
