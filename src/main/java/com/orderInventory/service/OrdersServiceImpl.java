package com.orderInventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderInventory.dto.OrderStatusCountDto;
import com.orderInventory.dto.OrdersDto;
import com.orderInventory.entity.Orders;
import com.orderInventory.exception.ResourceNotFoundException;
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
	

	@Override
	public List<OrderStatusCountDto> getOrderStatusCount() {
		
		List<Object[]> result= ordersRepository.getOrderStatusCount();
		
		List<OrderStatusCountDto> orderStatusCount = result.stream()
				.map(objects -> new OrderStatusCountDto((String) objects[0], (Long) objects[1]))
                .collect(Collectors.toList());
		
		return orderStatusCount;
	}
	
	
	@Override
	public List<OrdersDto> getOrdersByStoreName(String store) throws ResourceNotFoundException {
		
		List<Orders> orders= ordersRepository.findByStoreId_StoreName(store);
		
		if (orders.isEmpty()) {
			
            throw new ResourceNotFoundException("No Store found with name: " + store);
        } 
		else 
        {
            // Mapping each order to OrdersDto
        	
            List<OrdersDto> ordersDtoList = orders.stream()
                    .map(order -> {
                        OrdersDto ordersDto = new OrdersDto();
                        ordersDto.setOrderId(order.getOrderId());
                        ordersDto.setOrderStatus(order.getOrderStatus());
                        ordersDto.setStoreName(order.getStoreId().getStoreName());
                        ordersDto.setWebAddress(order.getStoreId().getWebAddress());
                        return ordersDto;
                    })
                    .collect(Collectors.toList());

            return ordersDtoList;
        }	
		
	}

}
