package com.example.demo; // Defines the package for test cases

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.PaymentApplication;
import com.cts.dto.Booking;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PaymentIdNotFoundException;
import com.cts.feignclient.BookingClient;
import com.cts.model.Payment;
import com.cts.repository.PaymentRepository;
import com.cts.service.PaymentServiceImpl;

@SpringBootTest(classes = PaymentApplication.class) // Marks this as a Spring Boot test class
class PaymentApplicationTests {

	@Mock
	private PaymentRepository repository; // Mock repository for Payment entity

	@Mock
	private BookingClient bookingClient; // Mock Feign Client for Booking interactions

	@InjectMocks
	private PaymentServiceImpl service; // Injects mocks into PaymentServiceImpl

	@Test
	void testAddPayment_Success() throws BookingIdNotFoundException {
		Payment payment = new Payment(1, 15, 4000, "paid", "Credit Card", 4);
		Booking booking = new Booking(1, 30, LocalDate.parse("2025-05-11"), LocalDate.parse("2025-05-13"), "Pending", 2,
				2, 3);

		when(bookingClient.viewBookingById(payment.getBookingId())).thenReturn(booking); // Mock booking retrieval
		when(repository.save(payment)).thenReturn(payment); // Mock payment save operation

		String response = service.addPayment(payment);
		assertEquals("Payment successfull!!", response); // Validate response

		verify(bookingClient, times(1)).updateBooking(any(Booking.class)); // Ensure booking is updated
	}

	@Test
	void testAddPayment_BookingNotFound() throws BookingIdNotFoundException {
		Payment payment = new Payment(1, 15, 4000, "paid", "Credit Card", 4);

		when(bookingClient.viewBookingById(payment.getBookingId())).thenReturn(null); // Simulate missing booking

		assertThrows(BookingIdNotFoundException.class, () -> service.addPayment(payment)); // Verify exception thrown
		verify(repository, never()).save(any(Payment.class)); // Ensure payment is not saved
	}

	@Test
	void testViewPaymentByValidId() throws PaymentIdNotFoundException {
		int paymentId = 1;
		Payment payment = new Payment(paymentId, 15, 4000, "paid", "Credit Card", 4);

		when(repository.findById(paymentId)).thenReturn(Optional.of(payment)); // Mock payment retrieval

		Payment result = service.viewPaymentById(paymentId);
		assertNotNull(result); // Validate response
		assertEquals(4000, result.getAmount()); // Check payment amount

		verify(repository, times(1)).findById(paymentId); // Ensure repository interaction
	}

	@Test
	void testViewPaymentByInvalidId() {
		int paymentId = 999;
		when(repository.findById(paymentId)).thenReturn(Optional.empty()); // Simulate invalid payment ID

		assertThrows(PaymentIdNotFoundException.class, () -> service.viewPaymentById(paymentId)); // Verify exception
																									// thrown
		verify(repository, times(1)).findById(paymentId); // Ensure repository interaction
	}

	@Test
	void testViewAllPayments() {
		Payment payment1 = new Payment(1, 15, 4000, "paid", "Credit Card", 4);
		Payment payment2 = new Payment(2, 16, 9000, "paid", "GPay", 5);

		when(repository.findAll()).thenReturn(Arrays.asList(payment1, payment2)); // Mock retrieval of all payments

		List<Payment> payments = service.viewAllPayment();
		assertEquals(2, payments.size()); // Validate response size

		verify(repository, times(1)).findAll(); // Ensure repository interaction
	}
}
