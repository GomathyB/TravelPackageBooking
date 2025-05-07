package com.cts.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Review {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
	@SequenceGenerator(name = "review_seq", sequenceName = "review_sequence", initialValue = 1, allocationSize = 1)
	@Id
	private int reviewId;
	private int packageId;
	private int rating;
	private String comment;
	@Column(name="timestamp", updatable=false)
	private LocalDateTime timestamp;
	private int userId;
	@PrePersist	// This annotation is used to run a piece of code before a entity is created
	protected void onCreate()
	{
		this.timestamp=LocalDateTime.now();	// For the review the local date and time is automatically inserted
	}
}
