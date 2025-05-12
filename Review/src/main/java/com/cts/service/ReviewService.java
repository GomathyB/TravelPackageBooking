package com.cts.service; // Defines the package for the Review Service interface

import java.util.List;

import com.cts.dto.ReviewUserDTOResponse;
import com.cts.exception.ReviewNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Review;

public interface ReviewService {

	// Adds a new review to the system
	public abstract String addReview(Review review);

	// Deletes a review by its ID
	public abstract String deleteReview(int reviewId);

	// Retrieves review details by ID, throws exception if review is not found
	public abstract Review viewReviewById(int reviewId) throws ReviewNotFoundException;

	// Retrieves all reviews
	public abstract List<Review> viewAllReview();

	// Fetches user details associated with a specific review ID, throws exception
	// if user is not found
	public abstract ReviewUserDTOResponse viewUserById(int reviewId) throws UserNotFoundException, ReviewNotFoundException;

	// Retrieves reviews sorted by rating in descending order
	public abstract List<Review> findByRatingSorted();
}
