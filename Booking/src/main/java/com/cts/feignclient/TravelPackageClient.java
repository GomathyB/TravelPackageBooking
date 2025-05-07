package com.cts.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.dto.TravelPackage;
import com.cts.exception.PackageNotFoundException;

@FeignClient(name="TRAVELPACKAGE",path="/travelPackage")
public interface TravelPackageClient {
	@GetMapping("/viewPackageById/{pid}")
	public TravelPackage viewPackageById(@PathVariable("pid") int  packageId)throws PackageNotFoundException;
	@PutMapping("/updatePackage")
	public String updatePackage(@RequestBody TravelPackage travelPackage);
}
