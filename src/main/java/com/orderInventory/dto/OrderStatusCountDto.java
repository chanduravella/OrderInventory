package com.orderInventory.dto;

import lombok.Data;

@Data
public class OrderStatusCountDto {
	
	private String orderStatus;
	private long Count;
	
	public OrderStatusCountDto(String orderStatus,long count){
		this.orderStatus = orderStatus;
		this.Count = count;
	}

}
