package com.orderInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderInventory.entity.Products;
import com.orderInventory.service.ProductsService;

@RestController
public class ProductsController {
	
	@Autowired
	ProductsService productsService;
	
	
	@GetMapping("/api/v1/products/fetchAll")
	public List<Products> getAllProducts(){
		
		List<Products> products = productsService.getAllProducts();
		
		return products;		
	}
	
	
	@PostMapping("/api/v1/products/addProduct")
	public Products addNewproduct(@RequestBody Products product) {
		
		Products addProduct = productsService.addNewProduct(product);
		
		return addProduct;
		
	}
	
	
	
	@GetMapping("/api/v1/products/sort/byField")
	
	public List<Products> getSortedProductsByField(@RequestParam String field, @RequestParam(defaultValue = "asc")String sortBy){
		
		List<Products> products = productsService.getSortedProductsByField(field, sortBy);
		
		return products;
		
	}

}
