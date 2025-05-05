package com.cts.service;

import java.util.List;

import com.cts.exception.PackageNotFoundException;
import com.cts.model.TravelPackage;

public interface TravelPackageService {
	public abstract String addPackage(TravelPackage travelPackage);

	public abstract String updatePackage(TravelPackage travelPackage);

	public abstract String deletePackageById(int packageId);

	public abstract TravelPackage viewPackageById(int packageId)throws PackageNotFoundException;

	public abstract List<TravelPackage> viewAllPackage();

}
