package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.TravelPackage;
@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Integer> {

}
