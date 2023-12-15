package com.orderInventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderInventory.entity.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer>{
	
	List<Customers> findByEmailAddress(String email);
	List<Customers> findByFullName(String name);

}
