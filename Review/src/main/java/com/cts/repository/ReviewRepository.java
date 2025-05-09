package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.Review;
@Repository
public interface ReviewRepository extends JpaRepository <Review, Integer> {
	public abstract List<Review> findByOrderByRatingDesc();
	
}
