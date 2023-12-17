package com.orderInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	public ResponseEntity<List<Orders>> getAllOrders(){
		
		 List<Orders> allOrders= ordersService.getAllOrders();
		 return new ResponseEntity<List<Orders>>(allOrders,HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/orders/status")
	public ResponseEntity<List<OrderStatusCountDto>> getOrderStatusCount(){
		
		List<OrderStatusCountDto> orderStatusCount = ordersService.getOrderStatusCount();
		
		return new ResponseEntity<List<OrderStatusCountDto>>(orderStatusCount,HttpStatus.OK);
	}
	
	
	@GetMapping("/api/v1/orders/{store}")
	public ResponseEntity<List<OrdersDto>> getOrdersByStoreName(@PathVariable String store) throws ResourceNotFoundException{
		
		List<OrdersDto> ordersByStoreName = ordersService.getOrdersByStoreName(store);
		
		return new ResponseEntity<List<OrdersDto>>(ordersByStoreName,HttpStatus.OK);
		
	}

}
