package com.cts.controller;

import java.util.List;

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

@RestController
@RequestMapping("/travelPackage")
public class TravelPackageController {
	@Autowired
	TravelPackageService service;
	@PostMapping("/addpackage")
	public String addPackage(@Valid @RequestBody TravelPackage travelPackage)
	{
		return service.addPackage(travelPackage);
	}
	@PutMapping("/updatePackage")
	public String updatePackage(@Valid @RequestBody TravelPackage travelPackage)
	{
		return service.updatePackage(travelPackage);
	}
	@DeleteMapping("/deletePackageById/{pid}")
	public String deletePackageById( @PathVariable("pid") int packageId)
	{
		return service.deletePackageById(packageId);
	}
	@GetMapping("/viewPackageById/{pid}")
	public TravelPackage viewPackageById( @PathVariable("pid") int  packageId)throws PackageNotFoundException
	{
		return service.viewPackageById(packageId);
	}
	@GetMapping("/viewAllPackage")
	public List<TravelPackage> viewAllPackage()
	{
		return service.viewAllPackage();
	}
}
