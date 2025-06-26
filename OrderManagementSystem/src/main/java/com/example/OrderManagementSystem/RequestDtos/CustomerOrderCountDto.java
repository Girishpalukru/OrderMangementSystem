package com.example.OrderManagementSystem.RequestDtos;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerOrderCountDto implements Serializable{
	private Long customerId;
	private String customerName;
	private Long orderCount;
	public CustomerOrderCountDto(Long customerId, String customerName, Long orderCount) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.orderCount = orderCount;
		
	}
	

}
