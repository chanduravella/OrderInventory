package com.orderInventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderInventory.entity.Products;
import com.orderInventory.repository.ProductsRepository;

@Service
public class ProductsServiceImpl implements ProductsService{
	
	@Autowired
	ProductsRepository productsRepository;

	@Override
	public List<Products> getAllProducts() {
		
		List<Products> products= productsRepository.findAll();	
		
		return products;
	}

	@Override
	public Products addNewProduct(Products product) {
		
		Products addProduct = productsRepository.save(product);
				
		return addProduct;
	}

	
	@Override
	public List<Products> getSortedProductsByField(String field, String sortBy) {
		
		List<Products> products= productsRepository.findAllProductsByField(field, sortBy);
		
		return products;
	}
	
	
	
	

}
