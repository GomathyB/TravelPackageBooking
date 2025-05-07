package com.cts.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_seq")
	@SequenceGenerator(name = "package_seq", sequenceName = "package_sequence", initialValue = 1, allocationSize = 1)
	private int packageId;
	private String travelPackageName;
	private String category;
	private String place;
	private String inclusions;
	private int noOfDays;
	private int availability;
	private int price;
}
