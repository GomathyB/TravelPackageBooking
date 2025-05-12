package com.cts.repository; // Defines the package for the repository layer

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.Payment;

@Repository // Marks this interface as a Spring Data JPA repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	// Provides CRUD operations for the Payment entity with Integer as the primary
	// key type
}
