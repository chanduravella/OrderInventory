package com.orderInventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderInventory.dto.CustomerInputDto;
import com.orderInventory.dto.CustomerOutputDto;
import com.orderInventory.dto.ShipmentStatusCountDto;
import com.orderInventory.entity.Customers;
import com.orderInventory.entity.Login;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.repository.CustomersRepository;


@Service
public class CustomersServiceImpl implements CustomersService{
	
	@Autowired
	CustomersRepository customersRepository;

	@Override
	public List<Customers> getAllCustomers() throws ResourceNotFoundException {
		
		List<Customers> customers= customersRepository.findAll();
		
		if (customers.isEmpty()) {
			throw new ResourceNotFoundException("No Customer Data Found");
		}
		else {
			return customers;
		}
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
	public List<ShipmentStatusCountDto> getShipmentStatusWiseCustomerCount() throws ResourceNotFoundException {
		
		 List<Object[]> result = customersRepository.getShipmentStatusWiseCustomerCount();
		 
		 if (result.isEmpty()) {
			 
			 throw new ResourceNotFoundException("No Details Found");			 
		 }
		 else {
			 
		 List<ShipmentStatusCountDto> shipmentStatusCountList = result.stream()
	                .map(objects -> new ShipmentStatusCountDto((String) objects[0], (Long) objects[1]))
	                .collect(Collectors.toList());

	        return shipmentStatusCountList;
		 }
	}

	@Override
	public CustomerOutputDto addCustomerDto(CustomerInputDto customerIputDto) {
		
		//create customer instance
		
		Customers customers = new Customers();
		
		customers.setEmailAddress(customerIputDto.getEmailAddress());
		customers.setFullName(customerIputDto.getFullName());
		
		// create login instance
		
		Login login = new Login();
		
		login.setEmail(customerIputDto.getEmailAddress());
		login.setPassword(customerIputDto.getPassword());
		login.setCategory("customer");
		login.setLogin(false);
		
		customers.setLogin(login);
		
		Customers newCustomer = customersRepository.save(customers);
		
		CustomerOutputDto customerOutputDto = new CustomerOutputDto();
		
		customerOutputDto.setEmailAddress(newCustomer.getEmailAddress());
		customerOutputDto.setFullName(newCustomer.getFullName());
		
		return customerOutputDto;
	}

	@Override
	public Customers updateCustomerDto(CustomerInputDto customerIputDto) throws ResourceNotFoundException {
		
		Optional<Customers> optionalList= customersRepository.findById(customerIputDto.getCustomerId());
		
		if (optionalList.isPresent()) {
			
			Customers customer = optionalList.get();
			
			customer.setCustomerId(customerIputDto.getCustomerId());
			customer.setEmailAddress(customerIputDto.getEmailAddress());
			customer.setFullName(customerIputDto.getFullName());
			
			Login login = new Login();
			
			login.setEmail(customerIputDto.getEmailAddress());
			login.setPassword(customerIputDto.getPassword());
			login.setCategory("customer");
			
			customer.setLogin(login);
			
			Customers updatedCustomer = customersRepository.save(customer);
			
			return updatedCustomer;			
			
		}
		else {
			
			throw new ResourceNotFoundException("No Customer found with Id: "+customerIputDto.getCustomerId());
		}

	}
	
	

}
