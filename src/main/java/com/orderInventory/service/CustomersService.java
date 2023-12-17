package com.orderInventory.service;

import java.util.List;

import com.orderInventory.dto.ShipmentStatusCountDto;
import com.orderInventory.entity.Customers;
import com.orderInventory.exception.ResourceNotFoundException;

public interface CustomersService {
	
	List<Customers> getAllCustomers();
	Customers addNewCustomer(Customers customer);
	Customers updateCustomer(Customers customer)throws ResourceNotFoundException;
	String deleteCustomer(Customers customer)throws ResourceNotFoundException;
	List<Customers> getCustomersByEmailAddress(String email) throws ResourceNotFoundException;
	List<Customers> getCustomersByName(String name)throws ResourceNotFoundException;
	
	List<ShipmentStatusCountDto> getShipmentStatusWiseCustomerCount();

}
