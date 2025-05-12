package com.cts.repository; // Defines the package for the repository layer

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.Booking;

@Repository // Marks this interface as a Spring Data JPA repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	// Provides CRUD operations for Booking entity with Integer as the primary key
	// type
}
