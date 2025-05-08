package com.cts.service;

import java.util.List;

import com.cts.dto.ReviewUserDTOResponse;
import com.cts.exception.ReviewNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.model.Review;

public interface ReviewService {
	public abstract String addReview(Review review);

	public abstract String deleteReview(int reviewId);

	public abstract Review viewReviewById(int reviewId) throws ReviewNotFoundException;
	
	public abstract List<Review> viewAllReview();
	
	public abstract ReviewUserDTOResponse viewUserById(int reviewId) throws UserNotFoundException;

}
