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
import com.orderInventory.repository.CustomersRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMockitoTest {
	
	@InjectMocks
	CustomersServiceImpl customersServiceImpl;

	@MockBean
	CustomersRepository customersRepository;

	// Initialization of mock objects
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	

	@Test
	void testGetAllEmployees() {
		
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

}
