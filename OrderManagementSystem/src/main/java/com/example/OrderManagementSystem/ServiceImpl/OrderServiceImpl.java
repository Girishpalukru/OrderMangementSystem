package com.example.OrderManagementSystem.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.*;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.Entity.Order;
import com.example.OrderManagementSystem.Entity.OrderItem;
import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.Exceptions.ResourceNotFoundException;
import com.example.OrderManagementSystem.Repository.CustomerRepository;
import com.example.OrderManagementSystem.Repository.OrderRepository;
import com.example.OrderManagementSystem.Repository.ProductRepository;
import com.example.OrderManagementSystem.RequestDtos.CustomerOrderCountDto;
import com.example.OrderManagementSystem.RequestDtos.OrderItemRequestDto;
import com.example.OrderManagementSystem.RequestDtos.OrderRequestDto;
import com.example.OrderManagementSystem.ResponceDtos.OrderItemResponceDto;
import com.example.OrderManagementSystem.ResponceDtos.OrderResponceDto;
import com.example.OrderManagementSystem.service.OrderService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private ProductRepository productRepo;
	
//	TODO Placing a new order
	
	@Transactional
	public OrderResponceDto placeOrder(@Valid OrderRequestDto dto) throws BadRequestException {
		// checking customer details 
		Customer customer = customerRepo.findById(dto.getCustmerId())
				.orElseThrow(()->new ResourceNotFoundException("Customer not found"));
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderDate(LocalDateTime.now());
		List<OrderItem> orderItems = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;
		// checking stock, deduct stock
		for(OrderItemRequestDto itemDto : dto.getItems()) {
			Product product = productRepo.findById(itemDto.getProductId())
					.orElseThrow(()->new ResourceNotFoundException("Product not found"));
			
			// checking sufficient stock is available or not
			if(product.getStock()<itemDto.getQuantity()) {
				throw new BadRequestException("Insufficient stock for product :"+ product.getName());
			}
			// if product place deducting stock 
			product.setStock(product.getStock()- itemDto.getQuantity());
			productRepo.save(product);
			
			// create order
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setProduct(product);
			item.setQuantity(itemDto.getQuantity());
			item.setItemTotal(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
			
			total = total.add(item.getItemTotal());
			orderItems.add(item);
				
		}
		// save order
		order.setItems(orderItems);
		order.setTotalAmount(total);
		Order saved = orderRepo.save(order);
		return mapToResponceDTO(saved);
	
	}
//	TODO Fetching orders by customer ID
	
	public List<OrderResponceDto> getOrderByCustomer(Long customerId){
		Customer customer = customerRepo.findById(customerId).
				orElseThrow(()-> new ResourceNotFoundException("customer not found"));
		List<Order> orders= orderRepo.findByCustomer(customer);
		return orders.stream().map(order ->{
			OrderResponceDto dto = new OrderResponceDto();
			dto.setOrderId(order.getId());
			
			dto.setCustomerName(customer.getName());
			dto.setOrderDate(order.getOrderDate());
			dto.setTotalAmount(order.getTotalAmount());
			
			List<OrderItemResponceDto> items = order.getItems().stream().map(item -> {
				OrderItemResponceDto itemDto = new OrderItemResponceDto();
				itemDto.setProductName(item.getProduct().getName());
				itemDto.setQuantity(item.getQuantity());
				
				itemDto.setItemTotal(item.getItemTotal());
				return itemDto;
			}).collect(Collectors.toList());
			dto.setItems(items);
			return dto;
		}).collect(Collectors.toList());
	}
	
//	TODO Total orders placed by each customer
	public List<CustomerOrderCountDto> getTotaOrdersByCustomer(){
		return orderRepo.countOrdersPerCustomer();
	}
	
//	TODO Top 5 customers based on the number of order
	public List<CustomerOrderCountDto> getTop5Customers(){
		return orderRepo.findTopCustomersByOrderCount(PageRequest.of(0, 5));
	}
	
//	TODO Calculate total order amount
	public BigDecimal calculateOrderAmount(Long orderId) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(()->new ResourceNotFoundException("order not found"));
		return order.getTotalAmount();
	}
	
	// mapping order to DTO
	private OrderResponceDto mapToResponceDTO(Order order) {
		OrderResponceDto dto = new OrderResponceDto();
		dto.setOrderId(order.getId());
		dto.setCustomerName(order.getCustomer().getName());
		dto.setOrderDate(order.getOrderDate());
		dto.setTotalAmount(order.getTotalAmount());
		
		List<OrderItemResponceDto> items= order.getItems().stream().map(e->{
			OrderItemResponceDto d = new OrderItemResponceDto();
			d.setProductName(e.getProduct().getName());
			d.setQuantity(e.getQuantity());
			d.setItemTotal(e.getItemTotal());
			return d;
		}).collect(Collectors.toList());
		dto.setItems(items);
		return dto;
		
	}
}

