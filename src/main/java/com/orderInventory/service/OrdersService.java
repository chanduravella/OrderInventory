package com.orderInventory.service;

import java.util.List;

import com.orderInventory.dto.OrderStatusCountDto;
import com.orderInventory.dto.OrdersDto;
import com.orderInventory.entity.Orders;
import com.orderInventory.exception.ResourceNotFoundException;

public interface OrdersService {
	
	List<Orders> getAllOrders();
	List<OrderStatusCountDto> getOrderStatusCount();
	
	List<OrdersDto> getOrdersByStoreName(String store) throws ResourceNotFoundException;

}
