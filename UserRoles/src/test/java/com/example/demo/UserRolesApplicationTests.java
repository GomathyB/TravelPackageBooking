package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
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

@SpringBootTest(classes = UserRolesApplication.class) // Initializes Spring Boot test context
class UserRolesApplicationTests {

	@Mock
	UserRolesRepository repository; // Mock repository for UserRoles entity

	@InjectMocks
	UserRolesServiceImpl service; // Injects mocks into UserRolesServiceImpl

	@Test
	void addUser() {
		UserRoles user = new UserRoles(1, "Gomathy", "gomathy04@gmail.com", "Gomathy04", "Travel_Agent");
		Mockito.when(repository.save(user)).thenReturn(user); // Mock user save operation

		String response = service.addUser(user);
		assertEquals("User saved successfully", response); // Validate response
	}

	@Test
	void updateUser() {
		UserRoles user = new UserRoles(1, "Gomathy", "gomathy04@gmail.com", "Gomathy04", "Travel_Agent");
		Mockito.when(repository.save(user)).thenReturn(user); // Mock user update operation

		String response = service.updateUser(user);
		assertEquals("User updated successfully", response); // Validate response
	}

	@Test
	void deleteUser() {
		int userId = 1;
		Mockito.doNothing().when(repository).deleteById(userId); // Mock deletion behavior

		String response = service.deleteUser(userId);
		assertEquals("User deleted successfully!!!", response); // Validate response
	}

	@Test
	void viewUserByValidId() throws UserNotFoundException {
		int userId = 1;
		UserRoles mockUser = new UserRoles(1, "Gomathy", "gomathy04@gmail.com", "Gomathy04", "Travel_Agent");
		Mockito.when(repository.findById(userId)).thenReturn(Optional.of(mockUser)); // Mock user retrieval

		UserRoles response = service.viewUserById(userId);
		assertNotNull(response); // Validate response
		assertEquals("Gomathy", response.getName()); // Check user name
		assertEquals("gomathy04@gmail.com", response.getEmail()); // Check user email
		Mockito.verify(repository, Mockito.times(1)).findById(userId); // Ensure repository interaction
	}

	@Test
	void viewUserByInvalidId() {
		int userId = 999; // This ID doesn't exist
		Mockito.when(repository.findById(userId)).thenReturn(Optional.empty()); // Mock invalid user ID

		assertThrows(UserNotFoundException.class, () -> service.viewUserById(userId)); // Verify exception thrown
		Mockito.verify(repository, Mockito.times(1)).findById(userId); // Ensure repository interaction
	}
}
