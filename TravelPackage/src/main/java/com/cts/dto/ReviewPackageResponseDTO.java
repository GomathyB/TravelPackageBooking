package com.cts.dto;

import java.util.List;

import com.cts.model.TravelPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPackageResponseDTO {
	private List<Review> reviews;
	private TravelPackage travelPackage;
}
