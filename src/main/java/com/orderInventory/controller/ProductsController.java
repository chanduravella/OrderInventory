package com.orderInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Products>> getAllProducts(){
		
		List<Products> allProducts = productsService.getAllProducts();
		
		return new ResponseEntity<List<Products>>(allProducts,HttpStatus.OK);		
	}
	
	
	@PostMapping("/api/v1/products/addProduct")
	public ResponseEntity<String> addNewproduct(@RequestBody Products product) {
		
		productsService.addNewProduct(product);
		
		return new ResponseEntity<String>("Record Created Successfully",HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("/api/v1/products/sort/byField")
	
	public ResponseEntity<List<Products>> getSortedProductsByField(@RequestParam String field, @RequestParam(defaultValue = "asc")String sortBy){
		
		List<Products> sortProductsByField = productsService.getSortedProductsByField(field, sortBy);
		
		return new ResponseEntity<List<Products>>(sortProductsByField,HttpStatus.OK);
		
	}

}
