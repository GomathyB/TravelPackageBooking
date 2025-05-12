package com.cts.feignclient; // Defines the package for Feign Client interaction

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.exception.UserNotFoundException;
import com.cts.dto.UserRoles;

@FeignClient(name = "USERROLES", path = "/userRoles") // Declares this as a Feign Client for the "USERROLES" service
public interface BookingUserClient {

	@GetMapping("/viewUserById/{id}") // Defines an endpoint to fetch user details by ID
	public UserRoles viewUserById(@PathVariable("id") int userId) throws UserNotFoundException;
}
