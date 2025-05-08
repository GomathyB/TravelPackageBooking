package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.model.Review;

import jakarta.transaction.Transactional;
@Repository
public interface ReviewRepository extends JpaRepository <Review, Integer> {
	
}
