package com.example.OrderManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OrderManagementSystem.Entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{


}
