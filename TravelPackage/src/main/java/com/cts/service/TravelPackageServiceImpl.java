package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.PackageNotFoundException;
import com.cts.model.TravelPackage;
import com.cts.repository.TravelPackageRepository;
@Service
public class TravelPackageServiceImpl implements TravelPackageService {
	@Autowired
	TravelPackageRepository repository;
	Logger log = LoggerFactory.getLogger(TravelPackageServiceImpl.class);

	@Override
	public String addPackage(TravelPackage travelPackage) {
		TravelPackage newTravelPackage = repository.save(travelPackage);
		if (newTravelPackage != null) {
			log.info("New Travel Package is added");
			return "Travel Package added successfully!!";
		} else
			return "Something went wrong!!!";
	}

	@Override
	public String updatePackage(TravelPackage travelPackage) {
		TravelPackage updated = repository.save(travelPackage);
		if (updated != null) {
			log.info("Updated Travel Package"); // Log message for update existing package
			return "Travel Package updated successfully";
		} else
			return "Something went wrong!!!";
	}

	@Override
	public String deletePackageById(int packageId) {
		repository.deleteById(packageId);
		log.info("A Travel package is deleted"); // Log message for deleting existing package
		return "Travel Package deleted successfully!!!";
	}

	@Override
	public TravelPackage viewPackageById(int packageId) throws PackageNotFoundException {
		Optional<TravelPackage> optional = repository.findById(packageId);
		if (optional.isPresent())
			return optional.get(); // Displaying the existing package
		else
			throw new PackageNotFoundException("Travel Package ID is invalid");
	}

	@Override
	public List<TravelPackage> viewAllPackage() {
		return repository.findAll();
	}

}
