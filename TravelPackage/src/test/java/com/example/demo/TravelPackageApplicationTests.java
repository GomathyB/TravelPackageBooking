package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.TravelPackageApplication;
import com.cts.dto.Review;
import com.cts.exception.PackageNotFoundException;
import com.cts.feignClient.ReviewClient;
import com.cts.model.TravelPackage;
import com.cts.repository.TravelPackageRepository;
import com.cts.service.TravelPackageServiceImpl;

@SpringBootTest(classes = TravelPackageApplication.class) // Initializes the Spring Boot test context
class TravelPackageApplicationTests {

	@Mock
	private TravelPackageRepository repository; // Mock repository for TravelPackage entity

	@Mock
	private ReviewClient reviewClient; // Mock Feign Client for Review interactions

	@InjectMocks
	private TravelPackageServiceImpl service; // Injects mocks into TravelPackageServiceImpl

	@Test
	void testAddPackage() {
		TravelPackage packageObj = new TravelPackage(1, "Trip to Kochi", "Family trip", "Kochi",
				"Food, Stay, Travel, Site Seeing", 5, 20, 3000);
		when(repository.save(packageObj)).thenReturn(packageObj); // Mock package save operation

		String response = service.addPackage(packageObj);
		assertEquals("Travel Package added successfully!!", response); // Validate response
	}

	@Test
	void testUpdatePackage() {
		TravelPackage packageObj = new TravelPackage(1, "Trip to Kochi", "Family trip", "Kochi",
				"Food, Stay, Travel, Site Seeing", 5, 20, 3000);
		when(repository.save(packageObj)).thenReturn(packageObj); // Mock package update operation

		String response = service.updatePackage(packageObj);
		assertEquals("Travel Package updated successfully", response); // Validate response
	}

	@Test
	void testDeletePackageById_WithReviews() {
		int packageId = 1;
		LocalDateTime timestamp1 = LocalDateTime.parse("2025-05-07T17:24:51.818214");
		LocalDateTime timestamp2 = LocalDateTime.parse("2025-05-08T17:24:51.818214");
		Review review1 = new Review(1, 1, 5, "The trip is highly recommended", timestamp1, 1);
		Review review2 = new Review(2, 1, 5, "The trip is highly recommended", timestamp2, 1);

		when(reviewClient.viewAllReview()).thenReturn(Arrays.asList(review1, review2)); // Mock retrieval of reviews

		String response = service.deletePackageById(packageId);
		assertEquals("Package along with reviews deleted", response); // Validate response

		verify(reviewClient, times(2)).deleteReview(anyInt()); // Ensure reviews are deleted
		verify(repository, times(1)).deleteById(packageId); // Ensure package deletion
	}

	@Test
	void testDeletePackageById_NoReviews() {
		int packageId = 2;
		when(reviewClient.viewAllReview()).thenReturn(Arrays.asList()); // Mock empty review list

		String response = service.deletePackageById(packageId);
		assertEquals("Travel Package deleted successfully and no reviews for the package!!!", response); // Validate
																											// response

		verify(repository, times(1)).deleteById(packageId); // Ensure package deletion
	}

	@Test
	void testViewPackageByValidId() throws PackageNotFoundException {
		int packageId = 1;
		TravelPackage packageObj = new TravelPackage(1, "Trip to Kochi", "Family trip", "Kochi",
				"Food, Stay, Travel, Site Seeing", 5, 20, 3000);
		when(repository.findById(packageId)).thenReturn(Optional.of(packageObj)); // Mock retrieval by ID

		TravelPackage response = service.viewPackageById(packageId);
		assertNotNull(response); // Validate response
		assertEquals("Trip to Kochi", response.getTravelPackageName()); // Check package name
		verify(repository, times(1)).findById(packageId); // Ensure repository interaction
	}

	@Test
	void testViewPackageByInvalidId() {
		int packageId = 999;
		when(repository.findById(packageId)).thenReturn(Optional.empty()); // Mock invalid package ID

		assertThrows(PackageNotFoundException.class, () -> service.viewPackageById(packageId)); // Verify exception
																								// thrown
		verify(repository, times(1)).findById(packageId); // Ensure repository interaction
	}

	@Test
	void testViewAllPackage() {
		TravelPackage package1 = new TravelPackage(1, "Trip to Kochi", "Family trip", "Kochi",
				"Food, Stay, Travel, Site Seeing", 5, 20, 3000);
		TravelPackage package2 = new TravelPackage(2, "Trip to Saudi Arabia", "International trip", "Saudi Arabia",
				"Food, Stay, Travel, Site Seeing", 2, 20, 30000);
		when(repository.findAll()).thenReturn(Arrays.asList(package1, package2)); // Mock retrieval of all packages

		List<TravelPackage> packages = service.viewAllPackage();
		assertEquals(2, packages.size()); // Validate response size
		verify(repository, times(1)).findAll(); // Ensure repository interaction
	}
}
