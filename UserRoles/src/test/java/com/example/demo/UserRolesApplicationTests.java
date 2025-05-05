package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.UserRolesApplication;
import com.cts.exception.UserNotFoundException;
import com.cts.model.UserRoles;
import com.cts.repository.UserRolesRepository;
import com.cts.service.UserRolesServiceImpl;

@SpringBootTest(classes = UserRolesApplication.class)
class UserRolesApplicationTests {
	@Mock
	UserRolesRepository repository;

	@InjectMocks
	UserRolesServiceImpl service;

	@Test
	void addUser() {
		UserRoles user = new UserRoles(1, "Gomathy", "gomathy04@gmail.com", "Gomathy04", "Travel_Agent");
		Mockito.when(repository.save(user)).thenReturn(user);

		String response = service.addUser(user);
		assertEquals("User saved successfully", response);

	}

	@Test
	void updateUser() {
		UserRoles user = new UserRoles(1, "Gomathy", "gomathy04@gmail.com", "Gomathy04", "Travel_Agent");
		Mockito.when(repository.save(user)).thenReturn(user);

		String response = service.updateUser(user);
		assertEquals("User updated successfully", response);

	}

	@Test
	void deleteUser() {
		int userId = 1;
		Mockito.doNothing().when(repository).deleteById(userId);
		String response = service.deleteUser(userId);
		assertEquals("User deleted successfully!!!", response);

	}

	@Test
	void viewUserByValidId() throws UserNotFoundException {
		int userId = 1;
		UserRoles mockUser = new UserRoles(1, "Gomathy", "gomathy04@gmail.com", "Gomathy04", "Travel_Agent");
		Mockito.when(repository.findById(userId)).thenReturn(Optional.of(mockUser));
		UserRoles response = service.viewUserById(userId);
		assertNotNull(response);
		assertEquals("Gomathy", response.getName());
		assertEquals("gomathy04@gmail.com", response.getEmail());
		Mockito.verify(repository, Mockito.times(1)).findById(userId);
	}

	void viewUserByInvalidId() {
	    int userId = 999; //This ID doesn't exist
	    // Mock repository behavior for findById() returning empty Optional
	    Mockito.when(repository.findById(userId)).thenReturn(Optional.empty());
	    // Expect UserNotFoundException when calling service method
	    assertThrows(UserNotFoundException.class, () -> service.viewUserById(userId));
	    // Verify that findById() was called exactly once
	    Mockito.verify(repository, Mockito.times(1)).findById(userId);
	}
}
