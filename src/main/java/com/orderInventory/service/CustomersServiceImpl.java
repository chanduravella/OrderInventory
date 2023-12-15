package com.orderInventory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderInventory.entity.Customers;
import com.orderInventory.exception.CustomerNotFoundException;
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
	public String addNewCustomer(Customers customer) {
		
		customersRepository.save(customer);
				
		return "Record Created Successfully";
	}

	@Override
	public String updateCustomer(Customers customer) throws CustomerNotFoundException{
		
		if (customersRepository.existsById(customer.getCustomerId())) {
			
			customersRepository.save(customer);
			
			return "Record Updated Succesfully";
			
			 
		}
		else {
		
		throw new CustomerNotFoundException("No Customer found with id: "+customer.getCustomerId());
		}
	}
	
	
	@Override
	public String deleteCustomer(Customers customer) throws CustomerNotFoundException {
		
		if (customersRepository.existsById(customer.getCustomerId())) {
			
			customersRepository.delete(customer);
			
			return "Record Deleted Succesfully";
		}
		else {
			throw new CustomerNotFoundException("No Customer Found with id: "+customer.getCustomerId());
		}
		
	}
	
	
	@Override
	public List<Customers> getCustomersByEmailAddress(String email) throws CustomerNotFoundException{
		
		 List<Customers> customers= customersRepository.findByEmailAddress(email);
		 
		 if (customers.isEmpty()) {
			 throw new CustomerNotFoundException("No Customer Found with Email Address: "+email);
		 }
		 else {
			 return customers;
		 }
		
	}
	
	
	@Override
	
	public List<Customers> getCustomersByName(String name) throws CustomerNotFoundException{
		
		 List<Customers> customers= customersRepository.findByFullName(name);
		 
		 if (customers.isEmpty()) {
			 throw new CustomerNotFoundException("No Customer Found with Name: "+name);
		 }
		 else {
			 return customers;
		 }
		
	}
	
	

}
