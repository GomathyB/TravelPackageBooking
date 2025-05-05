package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.UserRoles;

@Repository // Marks this interface as a Spring-managed repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
	// JpaRepository provides built-in CRUD methods (save, findById, delete, etc.)
}
