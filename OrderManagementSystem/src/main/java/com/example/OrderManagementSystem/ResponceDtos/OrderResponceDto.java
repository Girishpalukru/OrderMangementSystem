package com.example.OrderManagementSystem.ResponceDtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponceDto {
	
	private Long orderId;
	private String customerName;
	private LocalDateTime orderDate;
	private BigDecimal totalAmount;
	private List<OrderItemResponceDto> items;
	
}
