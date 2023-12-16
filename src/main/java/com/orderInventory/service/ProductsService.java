package com.orderInventory.service;

import java.util.List;

import com.orderInventory.entity.Products;

public interface ProductsService {
	
	List<Products> getAllProducts();
	Products addNewProduct(Products product);
	List<Products> getSortedProductsByField(String field, String sortBy);

}
