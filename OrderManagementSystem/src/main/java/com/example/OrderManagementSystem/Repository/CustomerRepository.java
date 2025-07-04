package com.example.OrderManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OrderManagementSystem.Entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
