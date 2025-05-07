package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.ReviewUserDTOResponse;
import com.cts.dto.UserRoles;
import com.cts.exception.ReviewNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.feignclient.UserClient;
import com.cts.model.Review;
import com.cts.repository.ReviewRepository;
@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewRepository repository;
	@Autowired
	UserClient userClient;
	
	Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Override
	public String addReview(Review review) {
		Review newReview = repository.save(review);
		if (newReview != null) {
			log.info("New review is added");
			return "Review added successfully!!";
		} else
			return "Something went wrong!!!";
	}

	@Override
	public String deleteReview(int reviewId) {
		repository.deleteById(reviewId);
		log.info("A review is deleted"); // Log message for deleting existing review
		return "Review deleted successfully!!!";
	}

	@Override
	public Review viewReviewById(int reviewId) throws ReviewNotFoundException{
		Optional<Review> optional = repository.findById(reviewId);
		if (optional.isPresent())
			return optional.get(); // Displaying the existing package
		else
			throw new ReviewNotFoundException("Review ID is invalid");
	}

	@Override
	public List<Review> viewAllReview() {
		return repository.findAll();
	}

	@Override
	public ReviewUserDTOResponse viewUserById(int reviewId) throws UserNotFoundException {
		Review review=repository.findById(reviewId).get();
		int userId=review.getUserId();
		UserRoles user=userClient.viewUserById(userId);
		if(user!=null)
		{
			ReviewUserDTOResponse response=new ReviewUserDTOResponse(user,review);
			return response;
		}
		else
		{
			throw new UserNotFoundException("User ID not found");
		}
	}
	
	

}
