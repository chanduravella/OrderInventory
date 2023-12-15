package com.orderInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderInventory.entity.Orders;
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

}
