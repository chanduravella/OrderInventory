package com.orderInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderInventory.dto.ShipmentStatusCountDto;
import com.orderInventory.entity.Customers;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.service.CustomersService;

import jakarta.validation.Valid;

@RestController
public class CustomersController {
	
	@Autowired
	CustomersService customersService;
	
	
	@GetMapping("/api/v1/customers/fetchAll")
	
	public ResponseEntity<List<Customers>> getAllCustomers(){
		
		List<Customers> allCustomers = customersService.getAllCustomers();
		return new ResponseEntity<List<Customers>>(allCustomers,HttpStatus.OK);
	}
	
	
	@PostMapping("/api/v1/customers/addCustomer")
	
	public ResponseEntity<String> addNewCustomer(@RequestBody Customers customer) {
		
		customersService.addNewCustomer(customer);
		
		return new ResponseEntity<String>("Record Created Successfully",HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/api/v1/customers/updateCustomer")
	
	public ResponseEntity<String> updateCustomer(@RequestBody Customers customer) throws ResourceNotFoundException {
		
		customersService.updateCustomer(customer);
		return new ResponseEntity<String>("Record Updated Successfully",HttpStatus.OK);
		
	}
	
	@DeleteMapping("/api/v1/customers/deleteCustomers")
	
	public ResponseEntity<String> deleteCustomer(Customers customer) throws ResourceNotFoundException {
		
		customersService.deleteCustomer(customer);
		
		return new ResponseEntity<String>("Record Deleted Successfully",HttpStatus.OK);
	}
	
	
	@GetMapping("/api/v1/customers/{email}/byEmailAddress")
	
	public ResponseEntity<List<Customers>> getCustomersByEmailAddress(@PathVariable String email) throws ResourceNotFoundException{
		
		List<Customers> customersByEmail = customersService.getCustomersByEmailAddress(email);
		
		return new ResponseEntity<List<Customers>>(customersByEmail,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/api/v1/customers/{name}/byFullName")
	
	public ResponseEntity<List<Customers>> getCustomersByName(@PathVariable String name)throws ResourceNotFoundException{
		
		List<Customers> customersByName = customersService.getCustomersByName(name);
		
		return new ResponseEntity<List<Customers>>(customersByName,HttpStatus.OK);
		
	}
	
	@GetMapping("/api/v1/customers/shipment/status")
	
	public ResponseEntity<List<ShipmentStatusCountDto>> getShipmentStatusWiseCustomerCount(){
		
		List<ShipmentStatusCountDto> shipmentCount = customersService.getShipmentStatusWiseCustomerCount();
		
		return new ResponseEntity<List<ShipmentStatusCountDto>>(shipmentCount,HttpStatus.OK);
	}
	
	

}
