package com.example.demo; // Defines the package for test cases

// Importing necessary test libraries and assertions
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.dto.*;
import com.cts.exception.*;
import com.cts.feignclient.*;
import com.cts.model.Booking;
import com.cts.repository.BookingRepository;
import com.cts.service.BookingServiceImpl;

class BookingApplicationTests {

	@Mock
	private BookingRepository repository; // Mocking the BookingRepository

	@Mock
	private TravelPackageClient packageClient; // Mocking the TravelPackageClient

	@Mock
	private BookingUserClient bookingUserClient; // Mocking the BookingUserClient

	@InjectMocks
	private BookingServiceImpl bookingService; // Injecting mocks into the BookingServiceImpl

	private Booking booking;
	private TravelPackage travelPackage;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initializes the mocks
		booking = new Booking(1, 100, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 15), "Pending", 10, 2, 1);
		travelPackage = new TravelPackage(100, "Beach Vacation", "Luxury", "Goa", "Meals, Stay", 5, 20, 15000);
	}

	@Test
	void testAddBooking_Success() throws PackageNotFoundException {
		when(packageClient.viewPackageById(100)).thenReturn(travelPackage); // Mock API response
		when(repository.save(booking)).thenReturn(booking); // Mock repository save

		String result = bookingService.addBooking(booking);

		assertEquals("Booked package successfully!!", result);
		verify(packageClient, times(1)).updatePackage(travelPackage); // Ensure package is updated
		verify(repository, times(1)).save(booking); // Ensure booking is saved
	}

	@Test
	void testAddBooking_InsufficientAvailability() throws PackageNotFoundException {
		travelPackage.setAvailability(1); // Set availability lower than required
		when(packageClient.viewPackageById(100)).thenReturn(travelPackage);

		String result = bookingService.addBooking(booking);

		assertEquals("Availability is not sufficient", result);
	}

	@Test
	void testAddBooking_PackageNotFoundException() throws PackageNotFoundException {
		when(packageClient.viewPackageById(100)).thenReturn(null); // Simulate missing package

		assertThrows(PackageNotFoundException.class, () -> bookingService.addBooking(booking));
	}

	@Test
	void testViewBookingById_Success() throws BookingIdNotFoundException {
		when(repository.findById(1)).thenReturn(Optional.of(booking));

		Booking result = bookingService.viewBookingById(1);

		assertEquals(1, result.getBookingId()); // Validate returned booking ID
	}

	@Test
	void testViewBookingById_BookingIdNotFoundException() {
		when(repository.findById(1)).thenReturn(Optional.empty()); // Simulate missing booking

		assertThrows(BookingIdNotFoundException.class, () -> bookingService.viewBookingById(1));
	}

	@Test
	void testDeleteBookingById_Success() {
		String result = bookingService.deleteBookingById(1);

		assertEquals("Booking deleted successfully!!!", result);
		verify(repository, times(1)).deleteById(1); // Ensure deletion operation is triggered
	}

	@Test
	void testViewAllBookings_Success() {
		when(repository.findAll()).thenReturn(Arrays.asList(booking));

		List<Booking> result = bookingService.viewAllBooking();

		assertEquals(1, result.size()); // Validate number of bookings returned
	}

	@Test
	void testUpdateBooking_Success() {
		when(repository.save(booking)).thenReturn(booking);

		String result = bookingService.updateBooking(booking);

		assertEquals("Booking updated successfully!!", result);
	}

	@Test
	void testViewUserByBookingId_Success() throws UserNotFoundException {
		UserRoles user = new UserRoles(1, "John Doe", "john@example.com", "password", "USER");
		when(repository.findById(1)).thenReturn(Optional.of(booking));
		when(bookingUserClient.viewUserById(1)).thenReturn(user);

		BookingUserResponse result = bookingService.viewUserByBookingId(1);

		assertEquals("John Doe", result.getUser().getName()); // Validate user retrieval
	}

	@Test
	void testViewUserByBookingId_UserNotFoundException() throws UserNotFoundException {
		when(repository.findById(1)).thenReturn(Optional.of(booking));
		when(bookingUserClient.viewUserById(1)).thenReturn(null); // Simulate missing user

		assertThrows(UserNotFoundException.class, () -> bookingService.viewUserByBookingId(1));
	}

	@Test
	void testViewPackageByBookingId_Success() throws PackageNotFoundException, UserNotFoundException {
		when(repository.findById(1)).thenReturn(Optional.of(booking));
		when(packageClient.viewPackageById(100)).thenReturn(travelPackage);

		BookingPackageResponse result = bookingService.viewPackageByBookingId(1);

		assertEquals("Beach Vacation", result.getTravelPackage().getTravelPackageName()); // Validate package name
	}

	@Test
	void testViewPackageByBookingId_PackageNotFoundException() throws PackageNotFoundException {
		when(repository.findById(1)).thenReturn(Optional.of(booking));
		when(packageClient.viewPackageById(100)).thenReturn(null); // Simulate missing package

		assertThrows(PackageNotFoundException.class, () -> bookingService.viewPackageByBookingId(1));
	}
}
