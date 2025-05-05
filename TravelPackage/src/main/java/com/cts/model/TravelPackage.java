package com.cts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class TravelPackage {
	@Id
	@Column(name="travelId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_seq")
	@SequenceGenerator(name = "package_seq", sequenceName = "package_sequence", initialValue = 1, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int travelPackageId;
	String travelPackageName;
	String category;
	String place;
	String inclusions;
	int noOfDays;
	int availability;
	int price;
	int itineraryId;
}
