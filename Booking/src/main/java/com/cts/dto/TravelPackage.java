package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TravelPackage {
	private int packageId;
	private String travelPackageName;
	private String category;
	private String place;
	private String inclusions;
	private int noOfDays;
	private int availability;
	private int price;
}
