package com.cts.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

@SpringBootTest
class ReviewApplicationTests {
	@Mock
	private ReviewRepository repository;

	@Mock
	private UserClient userClient;

	@InjectMocks
	private ReviewServiceImpl service;

	@Test
	void testAddReview() {
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-07T17:24:51.818214");
		Review review = new Review(1, 1, 5, "The trip is highly recommended", timestamp1, 1);
		when(repository.save(review)).thenReturn(review);

		String response = service.addReview(review);
		assertEquals("Review added successfully!!", response);
	}

	@Test
	void testDeleteReview() {
		int reviewId = 1;
		doNothing().when(repository).deleteById(reviewId);

		String response = service.deleteReview(reviewId);
		assertEquals("Review deleted successfully!!!", response);

		verify(repository, times(1)).deleteById(reviewId);
	}

	@Test
	void testViewReviewByValidId() throws ReviewNotFoundException {
		int reviewId = 1;
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-09T17:24:51.818214");
		Review review = new Review(reviewId, 1, 5, "Amazing experience!",timestamp1,1);
		when(repository.findById(reviewId)).thenReturn(Optional.of(review));

		Review response = service.viewReviewById(reviewId);
		assertNotNull(response);
		assertEquals("Amazing experience!", response.getComment());

		verify(repository, times(1)).findById(reviewId);
	}

	@Test
	void testViewReviewByInvalidId() {
		int reviewId = 999;
		when(repository.findById(reviewId)).thenReturn(Optional.empty());

		assertThrows(ReviewNotFoundException.class, () -> service.viewReviewById(reviewId));

		verify(repository, times(1)).findById(reviewId);
	}

	@Test
	void testViewAllReviews() {
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-02T17:24:51.818214");
		LocalDateTime timestamp2 = LocalDateTime.parse("2025-04-07T17:24:51.818214");
		Review review1 = new Review(1, 1, 5, "Great journey!",timestamp1, 1);
		Review review2 = new Review(2, 2, 4, "Nice experience!",timestamp2,2);
		when(repository.findAll()).thenReturn(Arrays.asList(review1, review2));

		List<Review> reviews = service.viewAllReview();
		assertEquals(2, reviews.size());

		verify(repository, times(1)).findAll();
	}

	@Test
	void testViewUserByValidReviewId() throws UserNotFoundException {
		int reviewId = 1;
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-07T17:24:51.818214");
		Review review = new Review(reviewId, 1, 5, "Fantastic trip!",timestamp1, 1);
		UserRoles user = new UserRoles(1, "John Doe", "john@example.com", "password123", "Traveler");

		when(repository.findById(reviewId)).thenReturn(Optional.of(review));
		when(userClient.viewUserById(1)).thenReturn(user);

		ReviewUserDTOResponse response = service.viewUserById(reviewId);
		assertNotNull(response);
		assertEquals("John Doe", response.getUser().getName());
		assertEquals("Fantastic trip!", response.getReview().getComment());

		verify(repository, times(1)).findById(reviewId);
		verify(userClient, times(1)).viewUserById(1);
	}

	

}
