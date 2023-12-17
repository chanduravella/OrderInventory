package com.orderInventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderInventory.dto.ShipmentStatusCountDto;
import com.orderInventory.entity.Customers;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.repository.CustomersRepository;


@Service
public class CustomersServiceImpl implements CustomersService{
	
	@Autowired
	CustomersRepository customersRepository;

	@Override
	public List<Customers> getAllCustomers() {
		
		List<Customers> customers= customersRepository.findAll();
		
		return customers;
	}

	@Override
	public Customers addNewCustomer(Customers customer) {
		
		Customers addCustomer = customersRepository.save(customer);
				
		return addCustomer;
	}

	@Override
	public Customers updateCustomer(Customers customer) throws ResourceNotFoundException{
		
		if (customersRepository.existsById(customer.getCustomerId())) {
			
			Customers updateCustomer = customersRepository.save(customer);
			
			return updateCustomer;
			
			 
		}
		else {
		
		throw new ResourceNotFoundException("No Customer found with id: "+customer.getCustomerId());
		}
	}
	
	
	@Override
	public String deleteCustomer(Customers customer) throws ResourceNotFoundException {
		
		if (customersRepository.existsById(customer.getCustomerId())) {
			
		customersRepository.delete(customer);
		
		return "Record Deleted Succesfully";
		
		}
		else {
			throw new ResourceNotFoundException("No Customer Found with id: "+customer.getCustomerId());
		}
		
	}
	
	
	@Override
	public List<Customers> getCustomersByEmailAddress(String email) throws ResourceNotFoundException{
		
		 List<Customers> customers= customersRepository.findByEmailAddress(email);
		 
		 if (customers.isEmpty()) {
			 throw new ResourceNotFoundException("No Customer Found with Email Address: "+email);
		 }
		 else {
			 return customers;
		 }
		
	}
	
	
	@Override
	
	public List<Customers> getCustomersByName(String name) throws ResourceNotFoundException{
		
		 List<Customers> customers= customersRepository.findByFullName(name);
		 
		 if (customers.isEmpty()) {
			 throw new ResourceNotFoundException("No Customer Found with Name: "+name);
		 }
		 else {
			 return customers;
		 }
		
	}
	
	

	@Override
	public List<ShipmentStatusCountDto> getShipmentStatusWiseCustomerCount() {
		
		 List<Object[]> result = customersRepository.getShipmentStatusWiseCustomerCount();
	        
		 List<ShipmentStatusCountDto> shipmentStatusCountList = result.stream()
	                .map(objects -> new ShipmentStatusCountDto((String) objects[0], (Long) objects[1]))
	                .collect(Collectors.toList());

	        return shipmentStatusCountList;
	}
	
	

}
