package com.example.OrderManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.RequestDtos.ProductRequestDto;
import com.example.OrderManagementSystem.ServiceImpl.ProductServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductServiceImpl service;
	
//	TODO Adding Product and validate the Product details
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequestDto product){
		return new ResponseEntity<Product>(service.addProduct(product),HttpStatus.CREATED);
	}
	
//	TODO  Update product details using productId 
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequestDto product){
		return new ResponseEntity<Product>(service.updateProuct(id, product),HttpStatus.ACCEPTED);
	}
	
//	TODO Fetching all Products Details
	@GetMapping("/all_products")
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(service.getAllProducts());
	}
}
