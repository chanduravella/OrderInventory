package com.orderInventory.dto;

import lombok.Data;

@Data
public class CustomerInputDto {
	
	private int customerId;
	private String fullName;
	private String email;
	private String password;
	private String category;

}
