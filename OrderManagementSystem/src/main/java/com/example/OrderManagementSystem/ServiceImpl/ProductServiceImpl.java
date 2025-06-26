package com.example.OrderManagementSystem.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.Exceptions.ResourceNotFoundException;
import com.example.OrderManagementSystem.Repository.ProductRepository;
import com.example.OrderManagementSystem.RequestDtos.ProductRequestDto;
import com.example.OrderManagementSystem.service.ProductService;

import jakarta.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepo;
	
//	TODO Adding new product 
	public Product addProduct(@Valid ProductRequestDto dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		
		return productRepo.save(product);
	}
	
//	TODO Updating existing product 
	public Product updateProuct(Long id, @Valid ProductRequestDto dto) {
		Product existing = productRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Product not found"));
		existing.setName(dto.getName());
		existing.setPrice(dto.getPrice());
		existing.setStock(dto.getStock());
		return productRepo.save(existing);
	}
	
//	TODO fetching all products 
	public List<Product> getAllProducts(){
		
		return productRepo.findAll();
	}
	

}
