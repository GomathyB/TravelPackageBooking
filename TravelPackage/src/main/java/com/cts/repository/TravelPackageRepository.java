package com.cts.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.model.TravelPackage;

@Repository // Marks this interface as a repository for database interaction
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Integer> {

	public abstract List<TravelPackage> findByPlace(String place); // Retrieves packages by destination

	public abstract List<TravelPackage> findByPriceLessThanEqual(int price); // Retrieves packages filtered by price
}
