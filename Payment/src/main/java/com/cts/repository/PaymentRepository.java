package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
