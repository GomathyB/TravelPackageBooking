package com.cts.service;

import java.util.List;
import java.util.Set;

import com.cts.exception.PackageNotFoundException;
import com.cts.model.TravelPackage;

public interface TravelPackageService {
	public abstract String addPackage(TravelPackage travelPackage);

	public abstract String updatePackage(TravelPackage travelPackage);

	public abstract String deletePackageById(int packageId);

	public abstract TravelPackage viewPackageById(int packageId)throws PackageNotFoundException;

	public abstract List<TravelPackage> viewAllPackage();
	
	public abstract List<TravelPackage> viewPacakageByPlace(String place);
	
	public abstract List<TravelPackage> viewPackageByPriceLessOrEqual(int price);
	
	public abstract Set<TravelPackage> viewPacakgeBySortedReview();
	
	

}
