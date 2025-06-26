package com.example.OrderManagementSystem.service;

import java.util.List;

import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.RequestDtos.ProductRequestDto;

import jakarta.validation.Valid;

public interface ProductService {
	
	public Product addProduct(@Valid ProductRequestDto dto);
	public Product updateProuct(Long id, @Valid ProductRequestDto dto);
	public List<Product> getAllProducts();
	
}
