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
	

	@Test
	public void testGetAllEmployees() {
		
		List<Customers> customersLst = new ArrayList<>();
		
		Customers Customer1 = new Customers();
		Customers Customer2 = new Customers();
		
		customersLst.add(Customer1);
		customersLst.add(Customer2);
		
		// Mocking the customersServiceImpl to return the list of customersLst
		
		Mockito.when(customersServiceImpl.getAllCustomers()).thenReturn(customersLst);
		
		List<Customers> customersList = customersServiceImpl.getAllCustomers();
		
		//Checking the response of expected value with actual value 
		
		assertEquals(2, customersList.size());
	}
	
	
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

		
		
}

