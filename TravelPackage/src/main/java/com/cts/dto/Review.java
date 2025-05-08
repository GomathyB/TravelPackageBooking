package com.cts.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	private int reviewId;
	private int packageId;
	private int rating;
	private String comment;
	private LocalDateTime timestamp;
	private int userId;
}
