package com.example.OrderManagementSystem.ServiceImpl;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.Exceptions.ResourceNotFoundException;
import com.example.OrderManagementSystem.Repository.CustomerRepository;
import com.example.OrderManagementSystem.RequestDtos.CustomerRequestDto;
import com.example.OrderManagementSystem.service.CustomerService;

import jakarta.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	public Customer addCustomer(@Valid CustomerRequestDto dto) throws BadRequestException {
		
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		customer.setPhone(dto.getPhone());
	
		return customerRepo.save(customer);
		
	}
	
	public Customer updateCustomer(Long id, @Valid CustomerRequestDto dto) throws BadRequestException {
		// checking customer is there or not using customer id
		Customer existing = customerRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Customer not found"));
			
		existing.setName(dto.getName());
		existing.setEmail(dto.getEmail());
		existing.setPhone(dto.getPhone());
		
		 return customerRepo.save(existing);
	
	}
	
	public List<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
}
