package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.ReviewUserDTOResponse;
import com.cts.exception.ReviewNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Review;
import com.cts.service.ReviewService;
@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	ReviewService service;
	@PostMapping("/addReview")
	public String addReview(@RequestBody Review review)
	{
		return service.addReview(review);
	}
	@DeleteMapping("/deleteReview/{rid}")
	public String deleteReview(@PathVariable("rid") int reviewId)
	{
		return service.deleteReview(reviewId);
	}
	@GetMapping("/viewReviewById/{rid}")
	public Review viewReviewById(@PathVariable("rid") int reviewId) throws ReviewNotFoundException
	{
		return service.viewReviewById(reviewId);
	}
	@GetMapping("/viewAllReview")
	public List<Review> viewAllReview()
	{
		return service.viewAllReview();
	}
	@GetMapping("/viewUserByReviewId/{rid}")
	public ReviewUserDTOResponse viewUserById(@PathVariable("rid") int reviewId) throws UserNotFoundException
	{
		return service.viewUserById(reviewId);
	}
}
