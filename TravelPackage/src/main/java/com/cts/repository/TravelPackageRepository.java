package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.TravelPackage;
@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Integer> {
	
	public abstract List<TravelPackage> findByPlace(String place);
	
	public abstract List<TravelPackage> findByPriceLessThanEqual(int price);
	

}
