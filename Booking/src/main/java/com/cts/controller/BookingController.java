package com.cts.controller; // Defines the package for the Booking Controller

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cts.dto.BookingPackageResponse;
import com.cts.dto.BookingUserResponse;
import com.cts.exception.BookingIdNotFoundException;
import com.cts.exception.PackageNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Booking;
import com.cts.service.BookingServiceImpl;

import jakarta.validation.Valid;

@RestController // Marks this class as a REST Controller
@RequestMapping("/booking") // Defines base URL mapping for booking-related operations
public class BookingController {

    @Autowired
    BookingServiceImpl service; // Injects BookingServiceImpl to handle booking operations

    @PostMapping("/addBooking") // Adds a new booking (POST request)
    public String addBooking(@Valid @RequestBody Booking booking) throws PackageNotFoundException {
        return service.addBooking(booking);
    }

    @DeleteMapping("/deleteBooking/{bid}") // Deletes a booking by ID (DELETE request)
    public String deleteBookingById(@PathVariable("bid") int bookingId) {
        return service.deleteBookingById(bookingId);
    }

    @GetMapping("/viewBookingById/{bid}") // Retrieves booking details by ID (GET request)
    public Booking viewBookingById(@PathVariable("bid") int bookingId) throws BookingIdNotFoundException {
        return service.viewBookingById(bookingId);
    }

    @GetMapping("/viewAllBooking") // Retrieves all booking records (GET request)
    public List<Booking> viewAllBooking() {
        return service.viewAllBooking();
    }

    @PutMapping("/updateBooking") // Updates booking details (PUT request)
    public String updateBooking(@Valid @RequestBody Booking booking) {
        return service.updateBooking(booking);
    }

    @GetMapping("/viewUserByBookingId/{bid}") // Fetches user details linked to a booking (GET request)
    public BookingUserResponse viewUserByBookingId(@PathVariable("bid") int bookingId) throws UserNotFoundException {
        return service.viewUserByBookingId(bookingId);
    }

    @GetMapping("/viewPackageByBookingId/{bid}") // Fetches package details linked to a booking (GET request)
    public BookingPackageResponse viewPackageByBookingId(@PathVariable("bid") int bookingId)
            throws UserNotFoundException, PackageNotFoundException {
        return service.viewPackageByBookingId(bookingId);
    }

    @PutMapping("/cancelBooking/{bid}") // Cancels a booking (PUT request)
    public String cancelBooking(@PathVariable("bid") int bookingId) 
            throws BookingIdNotFoundException, PackageNotFoundException {
        return service.cancelBooking(bookingId);
    }
}
