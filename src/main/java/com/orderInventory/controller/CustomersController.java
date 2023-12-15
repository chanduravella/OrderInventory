package com.orderInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderInventory.entity.Customers;
import com.orderInventory.exception.CustomerNotFoundException;
import com.orderInventory.service.CustomersService;

@RestController
public class CustomersController {
	
	@Autowired
	CustomersService customersService;
	
	
	@GetMapping("/api/v1/customers/fetchAll")
	
	public List<Customers> getAllCustomers(){
		
		List<Customers> customers = customersService.getAllCustomers();
		return customers;
	}
	
	
	@PostMapping("/api/v1/customers/addCustomer")
	
	public String addNewCustomer(@RequestBody Customers customer) {
		
		return customersService.addNewCustomer(customer);
		
	}
	
	
	@PutMapping("/api/v1/customers/updateCustomer")
	
	public String updateCustomer(@RequestBody Customers customer) throws CustomerNotFoundException {
		
		return customersService.updateCustomer(customer);
		
	}
	
	@DeleteMapping("/api/v1/customers/deleteCustomers")
	
	public String deleteCustomer(Customers customer) throws CustomerNotFoundException {
		
		return customersService.deleteCustomer(customer);
	}
	
	
	@GetMapping("/api/v1/customers/{email}/byEmailAddress")
	
	public List<Customers> getCustomersByEmailAddress(@PathVariable String email) throws CustomerNotFoundException{
		
		List<Customers> customers = customersService.getCustomersByEmailAddress(email);
		
		return customers;
		
	}
	
	
	@GetMapping("/api/v1/customers/{name}/byFullName")
	
	public List<Customers> getCustomersByName(@PathVariable String name)throws CustomerNotFoundException{
		
		List<Customers> customers = customersService.getCustomersByName(name);
		
		return customers;
		
	}

}
