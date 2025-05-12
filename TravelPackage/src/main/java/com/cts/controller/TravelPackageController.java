package com.cts.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.PackageNotFoundException;
import com.cts.model.TravelPackage;
import com.cts.service.TravelPackageService;

import jakarta.validation.Valid;

@RestController // Marks this class as a REST controller
@RequestMapping("/travelPackage") // Defines the base URL mapping
public class TravelPackageController {

	@Autowired
	TravelPackageService service; // Injects TravelPackageService

	@PostMapping("/addpackage") // Adds a new travel package
	public String addPackage(@Valid @RequestBody TravelPackage travelPackage) {
		return service.addPackage(travelPackage);
	}

	@PutMapping("/updatePackage") // Updates an existing travel package
	public String updatePackage(@Valid @RequestBody TravelPackage travelPackage) {
		return service.updatePackage(travelPackage);
	}

	@DeleteMapping("/deletePackageById/{pid}") // Deletes a package by ID
	public String deletePackageById(@PathVariable("pid") int packageId) {
		return service.deletePackageById(packageId);
	}

	@GetMapping("/viewPackageById/{pid}") // Retrieves a package by ID
	public TravelPackage viewPackageById(@PathVariable("pid") int packageId) throws PackageNotFoundException {
		return service.viewPackageById(packageId);
	}

	@GetMapping("/viewAllPackage") // Retrieves all available packages
	public List<TravelPackage> viewAllPackage() {
		return service.viewAllPackage();
	}

	@GetMapping("/viewPacakageByPlace/{place}") // Retrieves packages by destination
	public List<TravelPackage> viewPacakageByPlace(@Valid @PathVariable String place) {
		return service.viewPacakageByPlace(place);
	}

	@GetMapping("/viewPackageByPriceLessOrEqual/{price}") // Retrieves packages filtered by price
	public List<TravelPackage> findByPriceLessThanOrEqual(@Valid @PathVariable int price) {
		return service.viewPackageByPriceLessOrEqual(price);
	}

	@GetMapping("/viewPacakgeBySortedReview") // Retrieves packages sorted by review ratings
	public Set<TravelPackage> viewPacakgeBySortedReview() {
		return service.viewPacakgeBySortedReview();
	}
}
