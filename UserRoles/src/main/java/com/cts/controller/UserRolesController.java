package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.UserNotFoundException;
import com.cts.model.UserRoles;
import com.cts.service.UserRolesService;

import jakarta.validation.Valid;

//This is the controller class to define the URLs
@RestController
@RequestMapping("/userRoles") // Base URL
public class UserRolesController {
	@Autowired
	UserRolesService service;// Injecting the service layer

	@PostMapping("/addUser") // Add a new user
	public String addUser(@Valid @RequestBody UserRoles user) {
		return service.addUser(user);
	}

	@PutMapping("/updateUser") // Update a existing user
	public String updateUser(@Valid @RequestBody UserRoles user) {
		return service.updateUser(user);
	}

	@DeleteMapping("/deleteUser/{id}") // Delete a existing user by their ID
	public String deleteUser(@PathVariable("id") int userId) {
		return service.deleteUser(userId);
	}

	@GetMapping("/viewUserById/{id}") // View a existing user by their ID
	public UserRoles viewUserById(@PathVariable("id") int userId) throws UserNotFoundException {
		return service.viewUserById(userId);
	}
}
