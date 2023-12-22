package com.orderInventory.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.orderInventory.entity.Customers;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.repository.CustomersRepository;

@SpringBootTest
public class CustomersServiceTest {
	
	@Autowired
	CustomersService customersService;
	
	@Autowired
	CustomersRepository customersRepository ;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeAll");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("AfterAll");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("BeforeEach");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("AfterEach");
	}
	
	
	@Test
	public void getAllCustomersTest() throws ResourceNotFoundException {
		
		List<Customers> customersList = customersService.getAllCustomers();
		
		if (customersList.isEmpty()) {
			
			assertThrows(ResourceNotFoundException.class, ()->customersService.getAllCustomers());			
		}
		else {
		
		Customers customer = customersList.get(0); //	1	tammy.bryant@internalmail	Tammy Bryant
		
		assertEquals(0,customer.getCustomerId()); 
		assertEquals("tammy.bryant@internalmail",customer.getEmailAddress());
		assertEquals("Tammy Bryant",customer.getFullName());
		
		}
		
		
	}
	

}
