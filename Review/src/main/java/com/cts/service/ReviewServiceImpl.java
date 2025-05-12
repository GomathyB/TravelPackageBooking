package com.cts.service; // Defines the package for the Review service implementation

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

@Service // Marks this class as a service component for business logic
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository; // Injects ReviewRepository for database operations

    @Autowired
    UserClient userClient; // Feign Client to interact with the User microservice

    Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class); // Logger for tracking operations

    @Override
    public String addReview(Review review) {
        Review newReview = repository.save(review); // Saves review record
        if (newReview != null) {
            log.info("New review is added");
            return "Review added successfully!!";
        } else {
            return "Something went wrong!!!";
        }
    }

    @Override
    public String deleteReview(int reviewId) {
        repository.deleteById(reviewId);
        log.info("A review is deleted"); // Log message for deleting review
        return "Review deleted successfully!!!";
    }

    @Override
    public Review viewReviewById(int reviewId) throws ReviewNotFoundException {
        Optional<Review> optional = repository.findById(reviewId); // Searches review by ID
        log.info("Searching for a review");
        if (optional.isPresent()) {
            log.info("Review details found");
            return optional.get();
        } else {
            log.info("Review details not found!! Review ID is invalid");
            throw new ReviewNotFoundException("Review ID is invalid");
        }
    }

    @Override
    public List<Review> viewAllReview() {
        return repository.findAll(); // Fetches all review records
    }

    @Override
    public ReviewUserDTOResponse viewUserById(int reviewId) throws UserNotFoundException, ReviewNotFoundException {
        log.info("Searching for a review");
        Review review = repository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review ID is invalid"));
        int userId = review.getUserId();
        UserRoles user = userClient.viewUserById(userId);

        if (user != null) {
            return new ReviewUserDTOResponse(user, review);
        } else {
            throw new UserNotFoundException("User ID not found");
        }
    }

    @Override
    public List<Review> findByRatingSorted() {
        return repository.findByOrderByRatingDesc(); // Retrieves reviews sorted by rating
    }
}
