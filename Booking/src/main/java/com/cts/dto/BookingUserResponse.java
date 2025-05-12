package com.cts.dto; // Defines the package for the DTO (Data Transfer Object)

import com.cts.model.Booking; // Imports the Booking model

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class BookingUserResponse {
    Booking booking; // Holds booking details
    UserRoles user; // Holds user role details associated with the booking
}
