package com.cts.service;

import java.util.List;
import java.util.Set;
import com.cts.exception.PackageNotFoundException;
import com.cts.model.TravelPackage;

public interface TravelPackageService {

	public abstract String addPackage(TravelPackage travelPackage); // Adds a new travel package

	public abstract String updatePackage(TravelPackage travelPackage); // Updates an existing package

	public abstract String deletePackageById(int packageId); // Deletes a package by ID

	public abstract TravelPackage viewPackageById(int packageId) throws PackageNotFoundException; // Retrieves package
																									// details by ID

	public abstract List<TravelPackage> viewAllPackage(); // Retrieves all packages

	public abstract List<TravelPackage> viewPacakageByPlace(String place); // Retrieves packages by destination

	public abstract List<TravelPackage> viewPackageByPriceLessOrEqual(int price); // Retrieves packages filtered by
																					// price

	public abstract Set<TravelPackage> viewPacakgeBySortedReview(); // Retrieves packages sorted by review ratings
}
