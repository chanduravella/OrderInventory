package com.orderInventory.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.orderInventory.dto.OrderStatusCountDto;
import com.orderInventory.dto.ShipmentStatusCountDto;
import com.orderInventory.entity.Customers;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.repository.CustomersRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMockitoTest {
	
	@InjectMocks
	CustomersServiceImpl customersServiceImpl;

	@MockBean
	CustomersRepository customersRepository;

	// Initialization of mock objects
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	//test method to get all customers

	@Test
	public void testGetAllCustomers() throws ResourceNotFoundException {
		
		List<Customers> customersList = new ArrayList<>();
		
		// Mocking the customersServiceImpl to return the list of customersLst
		if (customersList.isEmpty()) {
			
			assertThrows(ResourceNotFoundException.class,()->customersServiceImpl.getAllCustomers());
		}
		else {
			
			Mockito.when(customersServiceImpl.getAllCustomers()).thenReturn(customersList);
			
			List<Customers> result = customersServiceImpl.getAllCustomers();
			
			//Checking the response of expected value with actual value 
			
			assertEquals(customersList, result);
		}
	}
	
	
	//test method to add customer
	
	@Test
	public void testAddCustomer() {
		
		Customers customer = new Customers();
		
		customer.setCustomerId(1);
		customer.setEmailAddress("chandu@gmail.com");
		customer.setFullName("chandu ravella");
		
		Mockito.when(customersServiceImpl.addNewCustomer(customer)).thenReturn(customer);
		
		Customers newCustomer = customersRepository.save(customer);
		
		assertEquals(customer,newCustomer);
		
	}
	
	
	// test method to update customer 
	
	@Test
	public void testUpdateCustomer() throws ResourceNotFoundException{
		
	    Customers existingCustomer = new Customers();
	    existingCustomer.setCustomerId(1);
	    existingCustomer.setEmailAddress("chandu@gmail.com");
	    existingCustomer.setFullName("chandu ravella");

	    Customers updatedCustomer = new Customers();
	    updatedCustomer.setCustomerId(1);
	    updatedCustomer.setEmailAddress("bruce@gmail.com");
	    updatedCustomer.setFullName("bruce wayne");
	    
	    // if customer exists return updated customer else throw exception
	    
	    if (customersRepository.existsById(existingCustomer.getCustomerId())){
	    	
	    	Mockito.when(customersServiceImpl.updateCustomer(existingCustomer)).thenReturn(updatedCustomer);
	    	
	    	Customers resultCustomer = customersServiceImpl.updateCustomer(existingCustomer);

	    	Mockito.verify(customersRepository, Mockito.times(1)).save(updatedCustomer);
	    
	    	assertEquals(updatedCustomer, resultCustomer);
	    }
	    else {
	    	assertThrows(ResourceNotFoundException.class, () -> customersServiceImpl.updateCustomer(existingCustomer));
	    }
	    
	}
	
	//test method to delete customer
	
	@Test
	void testDeleteCustomer() throws ResourceNotFoundException {
		
		Customers customer = new Customers();
		
		customer.setCustomerId(1);
		customer.setEmailAddress("chandu@gmail.com");
		customer.setFullName("chandu ravella");
		
		if (customersRepository.existsById(customer.getCustomerId())) {
			
			Mockito.when(customersServiceImpl.deleteCustomer(customer)).thenReturn("Record Deleted Succesfully");
			
			customersRepository.delete(customer);
			
			Mockito.verify(customersRepository, Mockito.times(1)).delete(customer);
			
		}
		else {
			
			assertThrows(ResourceNotFoundException.class, () -> customersServiceImpl.deleteCustomer(customer));
			
		}		
	}
	
	//test method to get customer by email id
	
	@Test
	void testGetCustomerByEmail() throws ResourceNotFoundException {
		
		String emailAddress = "ravella@gmail.com";
		
		List<Customers> customersList = new ArrayList();
		
		if (customersList.isEmpty()) {
			
			assertThrows(ResourceNotFoundException.class, () -> customersServiceImpl.getCustomersByEmailAddress(emailAddress));
		} 
		
		else {
			
			Mockito.when(customersServiceImpl.getCustomersByEmailAddress(emailAddress)).thenReturn(customersList);
			
			List<Customers> result = customersRepository.findByEmailAddress(emailAddress);
			
			assertEquals(customersList,result);
		}		
	}
	
	//test method to get customer by name
	
	@Test
	void testGetCustomerByName() throws ResourceNotFoundException {
		
		String fullName = "chandu ravella";
		
		List<Customers> customersList = new ArrayList<>();
		
		if (customersList.isEmpty()) {
			
			assertThrows(ResourceNotFoundException.class,()->customersServiceImpl.getCustomersByName(fullName));
			
		}
		else {
			
			Mockito.when(customersServiceImpl.getCustomersByName(fullName)).thenReturn(customersList);
			List<Customers> result = customersRepository.findByFullName(fullName);
			
			assertEquals(customersList,result);			
		}		
	}
	
	//test method for customer shipment status
	
	@Test
	void testGetCustomersShipmentsStatus() throws ResourceNotFoundException {
		
		List<ShipmentStatusCountDto> shipmentStatusDto = new ArrayList<>();			
		
		if(shipmentStatusDto.isEmpty()) {
						
			assertThrows(ResourceNotFoundException.class,()->customersServiceImpl.getShipmentStatusWiseCustomerCount());
			
		}
		else {
			
		Mockito.when(customersServiceImpl.getShipmentStatusWiseCustomerCount()).thenReturn(shipmentStatusDto);
		
		List<Object[]> result = customersRepository.getShipmentStatusWiseCustomerCount();
		
		//Checking the response of expected value with actual value 
		
		assertEquals(shipmentStatusDto, result);
		
		}		
	}	
}

