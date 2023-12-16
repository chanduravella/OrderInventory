package com.orderInventory.service;

import java.util.List;

import com.orderInventory.dto.ShipmentStatusCountDto;
import com.orderInventory.entity.Customers;
import com.orderInventory.exception.CustomerNotFoundException;

public interface CustomersService {
	
	List<Customers> getAllCustomers();
	String addNewCustomer(Customers customer);
	String updateCustomer(Customers customer)throws CustomerNotFoundException;
	String deleteCustomer(Customers customer)throws CustomerNotFoundException;
	List<Customers> getCustomersByEmailAddress(String email) throws CustomerNotFoundException;
	List<Customers> getCustomersByName(String name)throws CustomerNotFoundException;
	
	List<ShipmentStatusCountDto> getShipmentStatusWiseCustomerCount();

}
