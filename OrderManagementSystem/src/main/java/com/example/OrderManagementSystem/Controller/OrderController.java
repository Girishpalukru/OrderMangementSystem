package com.example.OrderManagementSystem.Controller;

import java.math.BigDecimal;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.OrderManagementSystem.RequestDtos.OrderRequestDto;
import com.example.OrderManagementSystem.ResponceDtos.OrderResponceDto;
import com.example.OrderManagementSystem.ServiceImpl.OrderServiceImpl;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderServiceImpl service;
	
//	TODO Placing new order and order contains customer details 
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderResponceDto> placeOrder(@Valid @RequestBody OrderRequestDto order) throws BadRequestException{
		return new ResponseEntity<OrderResponceDto>(service.placeOrder(order),HttpStatus.CREATED);
	}
	
//	TODO Fetch orders by customerId
	
	@GetMapping("/by-customer/{customerId}")
	public ResponseEntity<List<OrderResponceDto>> ordersByCustomer(@PathVariable Long customerId){
		return ResponseEntity.ok(service.getOrderByCustomer(customerId));
	}
	
//	TODO Calculate Total order amount
	
	@GetMapping("/totalAmount/{orderId}")
	public ResponseEntity<BigDecimal> totalOrderAmount(@PathVariable Long orderId){
		return ResponseEntity.ok(service.calculateOrderAmount(orderId));
	}
}
