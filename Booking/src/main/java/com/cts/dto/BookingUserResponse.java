package com.cts.dto;

import com.cts.model.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class BookingUserResponse {
	Booking booking;
	UserRoles user;
}
