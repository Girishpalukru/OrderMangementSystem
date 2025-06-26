package com.example.OrderManagementSystem.RequestDtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductRequestDto {
	@NotNull
	private String name;
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal price;
	@NotNull
	@Min(0)
	private Integer stock;
	
	
}
