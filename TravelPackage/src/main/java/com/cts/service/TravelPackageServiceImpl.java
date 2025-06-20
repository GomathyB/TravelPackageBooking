package com.cts.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.Review;
import com.cts.exception.PackageNotFoundException;
import com.cts.feignClient.ReviewClient;
import com.cts.model.TravelPackage;
import com.cts.repository.TravelPackageRepository;

@Service // Marks this class as a service component for business logic
public class TravelPackageServiceImpl implements TravelPackageService {

    @Autowired
    TravelPackageRepository repository; // Injects TravelPackageRepository for database operations

    @Autowired
    ReviewClient reviewClient; // Feign Client to interact with the Review microservice

    Logger log = LoggerFactory.getLogger(TravelPackageServiceImpl.class); // Logger for tracking operations

    /*
     *This method inputs a TravelPackage object
     *Saves the package
     *Returns a String 
     */
    @Override
    public String addPackage(TravelPackage travelPackage) {
        TravelPackage newTravelPackage = repository.save(travelPackage); // Saves travel package
        if (newTravelPackage != null) {
            log.info("New Travel Package is added");
            return "Travel Package added successfully!!";
        } else {
            return "Something went wrong!!!";
        }
    }

    /*
     *This method inputs a TravelPackage object
     *Updates the package
     *Returns a String 
     */
    @Override
    public String updatePackage(TravelPackage travelPackage) {
        TravelPackage updated = repository.save(travelPackage); // Updates travel package
        if (updated != null) {
            log.info("Updated Travel Package");
            return "Travel Package updated successfully";
        } else {
            return "Something went wrong!!!";
        }
    }

    /*
     *This method inputs a packageId(int)
     *Deletes the package along with its reviews from the review database
     *Returns a String 
     */
    @Override
    public String deletePackageById(int packageId) {
        boolean deleted = false;
        List<Review> reviews = reviewClient.viewAllReview(); // Fetches all reviews
        for (Review review : reviews) {
            if (review.getPackageId() == packageId) {
                deleted = true;
                reviewClient.deleteReview(review.getReviewId()); // Deletes related reviews
            }
        }
        repository.deleteById(packageId); // Deletes travel package
        if (deleted) {
            return "Package along with reviews deleted";
        } else {
            log.info("A Travel package is deleted");
            return "Travel Package deleted successfully and no reviews for the package!!!";
        }
    }

    /*
     *This method inputs a packageId(int)
     *Searches for the package
     *Returns the package object 
     */
    @Override
    public TravelPackage viewPackageById(int packageId) throws PackageNotFoundException {
        log.info("Searching for a Travel Package");
        Optional<TravelPackage> optional = repository.findById(packageId); // Fetches package by ID
        if (optional.isPresent()) {
            log.info("Travel Package found!!");
            return optional.get();
        } else {
            log.info("Travel Package not found!! ID is invalid");
            throw new PackageNotFoundException("Travel Package ID is invalid");
        }
    }

    /*
     *This method does not accept a input
     *Retrieve all the packages
     *Returns a list of packages 
     */
    @Override
    public List<TravelPackage> viewAllPackage() {
        return repository.findAll(); // Fetches all travel packages
    }

    /*
     *This method inputs a place(String)
     *Searches for the package with the particular place
     *Returns a list of packages 
     */
    @Override
    public List<TravelPackage> viewPacakageByPlace(String place) {
        return repository.findByPlace(place); // Fetches packages by place
    }

    /*
     *This method inputs the price(int)
     *Searches for package less than or equal
     *Returns a list of packages
     */
    @Override
    public List<TravelPackage> viewPackageByPriceLessOrEqual(int price) {
        return repository.findByPriceLessThanEqual(price); // Fetches packages filtered by price
    }

    /*
     *This method does not accept any input
     *Sorts the package based on reviews
     *Returns a set of packages
     */
    @Override
    public Set<TravelPackage> viewPacakgeBySortedReview() {
        List<Review> reviews = reviewClient.findByRatingSorted(); // Retrieves reviews sorted by rating
        Set<TravelPackage> packages = new HashSet<>();
        for (Review review : reviews) {
            int packageId = review.getPackageId();
            Optional<TravelPackage> packageOptional = repository.findById(packageId);
            packageOptional.ifPresent(packages::add); // Adds package if present
        }
        return packages;
    }
}
