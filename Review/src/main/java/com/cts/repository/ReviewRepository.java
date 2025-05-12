package com.cts.repository; // Defines the package for the repository layer

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.Review;

@Repository // Marks this interface as a Spring Data JPA repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	// Retrieves all reviews sorted by rating in descending order (highest rated
	// first)
	public abstract List<Review> findByOrderByRatingDesc();
}
