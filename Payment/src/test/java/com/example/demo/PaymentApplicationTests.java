package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

@SpringBootTest(classes=PaymentApplication.class)
class PaymentApplicationTests {
	@Mock
    private PaymentRepository repository;

    @Mock
    private BookingClient bookingClient;

    @InjectMocks
    private PaymentServiceImpl service;
	
    @Test
    void testAddPayment_Success() throws BookingIdNotFoundException {
        Payment payment = new Payment(1, 15,4000,"paid","Credit Card",4);
        Booking booking = new Booking(1, 30,  LocalDate.parse("2025-05-11"), 
        	    LocalDate.parse("2025-05-13"), "Pending",2,2,3);

        when(bookingClient.viewBookingById(payment.getBookingId())).thenReturn(booking);
        when(repository.save(payment)).thenReturn(payment);

        String response = service.addPayment(payment);
        assertEquals("Payment successfull!!", response);

        verify(bookingClient, times(1)).updateBooking(any(Booking.class));
    }
    
    @Test
    void testAddPayment_BookingNotFound() throws BookingIdNotFoundException {
        Payment payment = new Payment(1, 15,4000,"paid","Credit Card",4);

        when(bookingClient.viewBookingById(payment.getBookingId())).thenReturn(null);

        assertThrows(BookingIdNotFoundException.class, () -> service.addPayment(payment));
        verify(repository, never()).save(any(Payment.class));
    }

    @Test
    void testViewPaymentByValidId() throws PaymentIdNotFoundException {
        int paymentId = 1;
        Payment payment = new Payment(paymentId, 15,4000,"paid","Credit Card",4);

        when(repository.findById(paymentId)).thenReturn(Optional.of(payment));

        Payment result = service.viewPaymentById(paymentId);
        assertNotNull(result);
        assertEquals(4000, result.getAmount());

        verify(repository, times(1)).findById(paymentId);
    }
    
    @Test
    void testViewPaymentByInvalidId() {
        int paymentId = 999;
        when(repository.findById(paymentId)).thenReturn(Optional.empty());

        assertThrows(PaymentIdNotFoundException.class, () -> service.viewPaymentById(paymentId));
        verify(repository, times(1)).findById(paymentId);
    }

    @Test
    void testViewAllPayments() {
        Payment payment1 = new Payment(1, 15,4000,"paid","Credit Card",4);
        Payment payment2 = new Payment(2, 16,9000,"paid","GPay",5);

        when(repository.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        List<Payment> payments = service.viewAllPayment();
        assertEquals(2, payments.size());

        verify(repository, times(1)).findAll();
    }

}
