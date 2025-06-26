package com.example.OrderManagementSystem.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.Entity.Order;
import com.example.OrderManagementSystem.RequestDtos.CustomerOrderCountDto;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomer(Customer customer);
	
	@Query("SELECT new com.example.OrderManagementSystem.RequestDtos.CustomerOrderCountDto(c.id,c.name,COUNT(o))"+"From Order o JOIN o.customer c GROUP BY c.id, c.name")
	List<CustomerOrderCountDto> countOrdersPerCustomer();
	
	@Query("SELECT new com.example.OrderManagementSystem.RequestDtos.CustomerOrderCountDto(o.customer.id,o.customer.name, COUNT(o))"+"FROM Order o GROUP BY o.customer.id, o.customer.name ORDER BY COUNT(0) DESC")
	List<CustomerOrderCountDto> findTopCustomersByOrderCount(Pageable pageable);
}

	
