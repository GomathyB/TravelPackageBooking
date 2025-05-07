package com.cts.service;

import java.util.List;

import com.cts.exception.ReviewNotFoundException;
import com.cts.model.Review;

public interface ReviewService {
	public abstract String addReview(Review review);

	public abstract String deleteReview(int reviewId);

	public abstract Review viewReviewById(int reviewId) throws ReviewNotFoundException;
	
	public abstract List<Review> viewAllReview();
}
