package com.cts.dto;

import com.cts.model.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ReviewUserDTOResponse {
	private UserRoles user;
	private Review review;
}
