package com.orderInventory.service;

import com.orderInventory.dto.LoginInputDto;
import com.orderInventory.dto.LoginOutputDto;
import com.orderInventory.exception.InvalidCredentialsException;
import com.orderInventory.exception.ResourceNotFoundException;

public interface LoginService {
	
	LoginOutputDto customerLogin(LoginInputDto loginInputDto) throws InvalidCredentialsException, ResourceNotFoundException;

}
