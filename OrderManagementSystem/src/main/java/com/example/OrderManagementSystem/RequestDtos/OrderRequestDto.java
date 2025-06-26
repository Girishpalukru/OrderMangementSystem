package com.example.OrderManagementSystem.RequestDtos;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class OrderRequestDto {
	
	@NotNull
	private Long custmerId;
	@NotEmpty
	List<OrderItemRequestDto> items;
	
	
}
