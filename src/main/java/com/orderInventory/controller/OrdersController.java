package com.orderInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.orderInventory.dto.OrderStatusCountDto;
import com.orderInventory.dto.OrdersDto;
import com.orderInventory.entity.Orders;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.service.OrdersService;

@RestController
public class OrdersController {
	
	@Autowired
	OrdersService ordersService;
	
	@GetMapping("/api/v1/orders/getAllOrders")
	
	public List<Orders> getAllOrders(){
		
		 List<Orders> orders= ordersService.getAllOrders();
		 return orders;
	}
	
	@GetMapping("/api/v1/orders/status")
	public List<OrderStatusCountDto> getOrderStatusCount(){
		
		List<OrderStatusCountDto> result = ordersService.getOrderStatusCount();
		
		return result;
	}
	
	
	@GetMapping("/api/v1/orders/{store}")
	public List<OrdersDto> getOrdersByStoreName(@PathVariable String store) throws ResourceNotFoundException{
		
		List<OrdersDto> ordersDto = ordersService.getOrdersByStoreName(store);
		
		return ordersDto;
		
	}

}
