package com.example.OrderManagementSystem.ResponceDtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemResponceDto {
	private String productName;
	private Integer quantity;
	private BigDecimal itemTotal;
	
	
}
