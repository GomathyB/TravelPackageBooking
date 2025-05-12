package com.cts.controller; // Defines the package for the Review Controller

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cts.dto.ReviewUserDTOResponse;
import com.cts.exception.ReviewNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Review;
import com.cts.service.ReviewService;

import jakarta.validation.Valid;

@RestController // Marks this class as a REST controller
@RequestMapping("/review") // Defines the base URL mapping for review-related operations
public class ReviewController {

	@Autowired
	ReviewService service; // Injects ReviewService for handling review operations

	@PostMapping("/addReview") // Adds a new review (POST request)
	public String addReview(@Valid @RequestBody Review review) {
		return service.addReview(review);
	}

	@DeleteMapping("/deleteReview/{rid}") // Deletes a review by ID (DELETE request)
	public String deleteReview(@PathVariable("rid") int reviewId) {
		return service.deleteReview(reviewId);
	}

	@GetMapping("/viewReviewById/{rid}") // Retrieves review details by ID (GET request)
	public Review viewReviewById(@PathVariable("rid") int reviewId) throws ReviewNotFoundException {
		return service.viewReviewById(reviewId);
	}

	@GetMapping("/viewAllReview") // Retrieves all reviews (GET request)
	public List<Review> viewAllReview() {
		return service.viewAllReview();
	}

	@GetMapping("/viewUserByReviewId/{rid}") // Fetches user details based on review ID
	public ReviewUserDTOResponse viewUserById(@PathVariable("rid") int reviewId) throws UserNotFoundException, ReviewNotFoundException {
		return service.viewUserById(reviewId);
	}

	@GetMapping("/ratingSorted") // Retrieves reviews sorted by rating
	public List<Review> findByRatingSorted() {
		return service.findByRatingSorted();
	}
}
