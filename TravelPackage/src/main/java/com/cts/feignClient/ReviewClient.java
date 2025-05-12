package com.cts.feignClient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.cts.dto.Review;

@FeignClient(name = "REVIEW", path = "/review") // Defines Feign Client for the Review microservice
public interface ReviewClient {

	@GetMapping("/viewAllReview") // Retrieves all reviews
	public List<Review> viewAllReview();

	@DeleteMapping("/deleteReview/{rid}") // Deletes a review by ID
	public String deleteReview(@PathVariable("rid") int reviewId);

	@GetMapping("/ratingSorted") // Retrieves reviews sorted by rating
	public List<Review> findByRatingSorted();
}
