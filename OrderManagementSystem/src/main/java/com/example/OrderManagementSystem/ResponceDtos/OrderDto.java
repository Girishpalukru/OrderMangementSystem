package com.example.OrderManagementSystem.ResponceDtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDto {
	
	private Long id;
	private String productname;
	private LocalDateTime locaDateTime;
	private BigDecimal totalAmount;
	
	public OrderDto(Long id, String productname, LocalDateTime locaDateTime, BigDecimal totalAmount) {
		this.id = id;
		this.productname = productname;
		this.locaDateTime = locaDateTime;
		this.totalAmount = totalAmount;
	}

	
	
	
	
}
