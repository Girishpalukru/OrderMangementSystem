package com.example.OrderManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderManagementSystem.RequestDtos.CustomerOrderCountDto;
import com.example.OrderManagementSystem.ServiceImpl.OrderServiceImpl;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	@Autowired
	private OrderServiceImpl service;
	
//	TODO total orders placed by each customer 
	@GetMapping("/total_orders")
	public ResponseEntity<List<CustomerOrderCountDto>> gettotalOrdersByEachCustomer(){
		return ResponseEntity.ok(service.getTotaOrdersByCustomer());
	}
//	TODO top 5 customers based on the number of orders
	@GetMapping("/top_customers")
	public ResponseEntity<List<CustomerOrderCountDto>> top5Customers(){
		return ResponseEntity.ok(service.getTop5Customers());
	}
}
