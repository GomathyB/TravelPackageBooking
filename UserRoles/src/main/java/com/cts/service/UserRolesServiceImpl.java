package com.cts.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.UserNotFoundException;
import com.cts.model.UserRoles;
import com.cts.repository.UserRolesRepository;

@Service // Marks this class as a Spring Service component
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	UserRolesRepository repository;// Injecting the repository to interact with the database
	Logger log = LoggerFactory.getLogger(UserRolesServiceImpl.class);// Logger for tracking operations
	/*
	 * This method inputs the UserRoles object
	 * Save the user
	 * Returns a String
	 */
	@Override
	public String addUser(UserRoles user) {
		UserRoles saved = repository.save(user);
		if (saved != null) {
			log.info("New User is saved"); // Log message for add new user
			return "User saved successfully";
		} else
			return "Something went wrong!!!";
	}
	/*
	 * This method inputs a user object
	 * Updates the user
	 * Returns a string
	 */
	@Override
	public String updateUser(UserRoles user) {
		UserRoles updated = repository.save(user);
		if (updated != null) {
			log.info("Updated user"); // Log message for update existing user
			return "User updated successfully";
		} else
			return "Something went wrong!!!";
	}

	/*
	 * This method inputs a userId(int)
	 * Deletes a user
	 * Returns a string
	 */
	@Override
	public String deleteUser(int userId) {
		repository.deleteById(userId);
		log.info("A User is deleted"); // Log message for deleting existing user
		return "User deleted successfully!!!";
	}

	/*
	 * This method inputs a userId(int)
	 * Searches for the user
	 * Returns the user
	 */
	@Override
	public UserRoles viewUserById(int userId) throws UserNotFoundException {
		Optional<UserRoles> optional = repository.findById(userId);
		log.info("Searching for a user's details");
		if (optional.isPresent())
		{
			log.info("User details found");
			return optional.get(); // Displaying the existing user
		}
		else
		{
			log.info("User details not found!! UserId invalid");
			throw new UserNotFoundException("User ID is invalid"); // When invalid ID is passed, the exception is thrown
		}
	}

}
