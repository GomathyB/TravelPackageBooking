package com.cts.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.Review;

@FeignClient(name="REVIEW",path="/review")
public interface ReviewClient {
	@GetMapping("/viewAllReview")
	public List<Review> viewAllReview();
	@DeleteMapping("/deleteReview/{rid}")
	public String deleteReview(@PathVariable("rid") int reviewId);


}
