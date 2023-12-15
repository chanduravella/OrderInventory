package com.orderInventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderInventory.entity.Orders;
import com.orderInventory.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	
	@Autowired 
	OrdersRepository ordersRepository;

	@Override
	public List<Orders> getAllOrders() {
		
		 List<Orders> orders= ordersRepository.findAll();
		return orders;
	}

}
