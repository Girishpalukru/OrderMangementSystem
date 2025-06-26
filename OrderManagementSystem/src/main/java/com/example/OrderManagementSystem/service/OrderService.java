package com.example.OrderManagementSystem.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.coyote.BadRequestException;

import com.example.OrderManagementSystem.RequestDtos.CustomerOrderCountDto;
import com.example.OrderManagementSystem.RequestDtos.OrderRequestDto;
import com.example.OrderManagementSystem.ResponceDtos.OrderResponceDto;

import jakarta.validation.Valid;

public interface OrderService {
	
	public OrderResponceDto placeOrder(@Valid OrderRequestDto dto) throws BadRequestException;
	public List<OrderResponceDto> getOrderByCustomer(Long customerId);
	public List<CustomerOrderCountDto> getTotaOrdersByCustomer();
	public List<CustomerOrderCountDto> getTop5Customers();
	public BigDecimal calculateOrderAmount(Long orderId);
}
