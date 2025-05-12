package com.cts.feignclient; // Defines the package for the Feign Client

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.dto.TravelPackage;
import com.cts.exception.PackageNotFoundException;

@FeignClient(name = "TRAVELPACKAGE", path = "/travelPackage") // Declares this interface as a Feign Client for
																// "TRAVELPACKAGE" service
public interface TravelPackageClient {

	@GetMapping("/viewPackageById/{pid}") // Fetches a travel package by its ID
	public TravelPackage viewPackageById(@PathVariable("pid") int packageId) throws PackageNotFoundException;

	@PutMapping("/updatePackage") // Updates travel package details
	public String updatePackage(@RequestBody TravelPackage travelPackage);
}
