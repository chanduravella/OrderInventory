package com.orderInventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderInventory.dto.LoginInputDto;
import com.orderInventory.dto.LoginOutputDto;
import com.orderInventory.exception.InvalidCredentialsException;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/customers/login")
	public ResponseEntity<LoginOutputDto> customerLogin(@RequestBody LoginInputDto loginInputDto) throws InvalidCredentialsException,ResourceNotFoundException {
		
		LoginOutputDto loginOutputDto = loginService.customerLogin(loginInputDto);
		
		return new ResponseEntity<LoginOutputDto>(loginOutputDto,HttpStatus.OK);
	}
	
	
	@GetMapping("customers/logout/{email}")
	public ResponseEntity<LoginOutputDto> customerLogout(@PathVariable String email) throws ResourceNotFoundException{
		
		LoginOutputDto logout = loginService.customerLogout(email);
		
		return new ResponseEntity<LoginOutputDto>(logout,HttpStatus.OK);
		
		
	}

	
	
}
