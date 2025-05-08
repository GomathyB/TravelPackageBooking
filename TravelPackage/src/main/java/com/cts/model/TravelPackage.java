package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "Travel Package name is mandatory")
	@Size(min = 2, max = 70, message = "Travel Package name must be between 3 and 70 characters")
	private String travelPackageName;
	@NotBlank(message = "Mention the category of the package")
	@Size(min = 2, max = 70, message = "Category must be between 3 and 70 characters")
	private String category;
	@NotBlank(message = "Mention the place of the package")
	@Size(min = 2, max = 70, message = "Place must be between 3 and 70 characters")
	private String place;
	@NotBlank(message = "Mention the inclusions the package has")
	@Size(min = 2, max = 70, message = "Inclusions must be between 3 and 70 characters")
	private String inclusions;
	@Max(value = 10, message = "No of days should be at most 10")
	@Min(value = 1, message = "No of days should be at least 1")
	private int noOfDays;
	@Max(value = 100, message = "The availability should be at most 100")
	@Min(value = 5, message = "The availability should be at least 5")
	private int availability;
	@Max(value = 50000, message = "The price should be atmost 50000")
	@Min(value = 1000, message = "The price should be atleast 1000")
	private int price;
}
