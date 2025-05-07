package com.cts.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.UserRoles;
import com.cts.exception.UserNotFoundException;

@FeignClient(name="USERROLES",path="/userRoles")
public interface UserClient {
	@GetMapping("/viewUserById/{id}")
	public UserRoles viewUserById(@PathVariable("id") int userId) throws UserNotFoundException;

}
