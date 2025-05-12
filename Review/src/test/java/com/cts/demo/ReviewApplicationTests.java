package com.cts.demo; // Defines the package for test cases

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dto.ReviewUserDTOResponse;
import com.cts.dto.UserRoles;
import com.cts.exception.ReviewNotFoundException;
import com.cts.exception.UserNotFoundException;
import com.cts.feignclient.UserClient;
import com.cts.model.Review;
import com.cts.repository.ReviewRepository;
import com.cts.service.ReviewServiceImpl;

@SpringBootTest // Marks this class as a Spring Boot test
class ReviewApplicationTests {

	@Mock
	private ReviewRepository repository; // Mock repository for Review entity

	@Mock
	private UserClient userClient; // Mock Feign Client for User interactions

	@InjectMocks
	private ReviewServiceImpl service; // Injects mocks into ReviewServiceImpl

	@Test
	void testAddReview() {
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-07T17:24:51.818214");
		Review review = new Review(1, 1, 5, "The trip is highly recommended", timestamp1, 1);
		when(repository.save(review)).thenReturn(review); // Mock review save operation

		String response = service.addReview(review);
		assertEquals("Review added successfully!!", response); // Validate response
	}

	@Test
	void testDeleteReview() {
		int reviewId = 1;
		doNothing().when(repository).deleteById(reviewId); // Mock deletion behavior

		String response = service.deleteReview(reviewId);
		assertEquals("Review deleted successfully!!!", response); // Validate response

		verify(repository, times(1)).deleteById(reviewId); // Ensure delete operation occurred
	}

	@Test
	void testViewReviewByValidId() throws ReviewNotFoundException {
		int reviewId = 1;
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-09T17:24:51.818214");
		Review review = new Review(reviewId, 1, 5, "Amazing experience!", timestamp1, 1);
		when(repository.findById(reviewId)).thenReturn(Optional.of(review)); // Mock review retrieval

		Review response = service.viewReviewById(reviewId);
		assertNotNull(response); // Validate response
		assertEquals("Amazing experience!", response.getComment()); // Check review comment

		verify(repository, times(1)).findById(reviewId); // Ensure repository interaction
	}

	@Test
	void testViewReviewByInvalidId() {
		int reviewId = 999;
		when(repository.findById(reviewId)).thenReturn(Optional.empty()); // Simulate invalid review ID

		assertThrows(ReviewNotFoundException.class, () -> service.viewReviewById(reviewId)); // Verify exception thrown
		verify(repository, times(1)).findById(reviewId); // Ensure repository interaction
	}

	@Test
	void testViewAllReviews() {
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-02T17:24:51.818214");
		LocalDateTime timestamp2 = LocalDateTime.parse("2025-04-07T17:24:51.818214");
		Review review1 = new Review(1, 1, 5, "Great journey!", timestamp1, 1);
		Review review2 = new Review(2, 2, 4, "Nice experience!", timestamp2, 2);
		when(repository.findAll()).thenReturn(Arrays.asList(review1, review2)); // Mock retrieval of all reviews

		List<Review> reviews = service.viewAllReview();
		assertEquals(2, reviews.size()); // Validate response size

		verify(repository, times(1)).findAll(); // Ensure repository interaction
	}

	@Test
	void testViewUserByValidReviewId() throws UserNotFoundException, ReviewNotFoundException {
		int reviewId = 1;
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-07T17:24:51.818214");
		Review review = new Review(reviewId, 1, 5, "Fantastic trip!", timestamp1, 1);
		UserRoles user = new UserRoles(1, "John Doe", "john@example.com", "password123", "Traveler");

		when(repository.findById(reviewId)).thenReturn(Optional.of(review)); // Mock review retrieval
		when(userClient.viewUserById(1)).thenReturn(user); // Mock user retrieval

		ReviewUserDTOResponse response = service.viewUserById(reviewId);
		assertNotNull(response); // Validate response
		assertEquals("John Doe", response.getUser().getName()); // Check user name
		assertEquals("Fantastic trip!", response.getReview().getComment()); // Check review comment

		verify(repository, times(1)).findById(reviewId); // Ensure repository interaction
		verify(userClient, times(1)).viewUserById(1); // Ensure Feign Client interaction
	}
}
