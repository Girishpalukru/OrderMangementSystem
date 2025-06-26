package com.example.OrderManagementSystem.service;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.RequestDtos.CustomerRequestDto;

import jakarta.validation.Valid;

public interface CustomerService {
	
	public Customer addCustomer(@Valid CustomerRequestDto dto) throws BadRequestException;
	public Customer updateCustomer(Long id, @Valid CustomerRequestDto dto) throws BadRequestException;
	public List<Customer> getAllCustomers();
	
}
