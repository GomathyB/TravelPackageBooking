package com.cts.feignclient; // Defines the package for the Feign Client

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.UserRoles;
import com.cts.exception.UserNotFoundException;

@FeignClient(name = "USERROLES", path = "/userRoles") // Declares this interface as a Feign Client for "USERROLES"
														// microservice
public interface UserClient {

	@GetMapping("/viewUserById/{id}") // Fetches user details by ID (GET request)
	public UserRoles viewUserById(@PathVariable("id") int userId) throws UserNotFoundException;
}
