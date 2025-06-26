package com.example.OrderManagementSystem.Controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
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
import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.RequestDtos.CustomerRequestDto;
import com.example.OrderManagementSystem.ServiceImpl.CustomerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl service;
	
//	TODO Adding customer and validate the customer details
	@PostMapping("/add")
	public ResponseEntity<Customer> add(@Valid @RequestBody CustomerRequestDto customer) throws BadRequestException{
		
		return new ResponseEntity<Customer>(service.addCustomer(customer),HttpStatus.CREATED);
	}
	
//	TODO Update customer details and validate the customer details
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id,@Valid @RequestBody CustomerRequestDto customer) throws BadRequestException{
		return new ResponseEntity<Customer>(service.updateCustomer(id, customer),HttpStatus.ACCEPTED);
	}
	
//	TODO Fetch all customers details
	@GetMapping("/all_Customers")
	public ResponseEntity<List<Customer>> getAll(){
		return ResponseEntity.ok(service.getAllCustomers());
	}
}
