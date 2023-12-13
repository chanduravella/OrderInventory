package com.orderInventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderInventory.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository <Customers, Integer>{
	
	

}
