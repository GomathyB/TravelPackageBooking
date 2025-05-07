package com.cts.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.exception.UserNotFoundException;
import com.cts.dto.UserRoles;
@FeignClient(name="USERROLES",path="/userRoles")
public interface BookingUserClient {
	@GetMapping("/viewUserById/{id}") 
	public UserRoles viewUserById(@PathVariable("id") int userId) throws UserNotFoundException;
}
