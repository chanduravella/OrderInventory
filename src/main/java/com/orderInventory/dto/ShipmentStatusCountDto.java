package com.orderInventory.dto;

import lombok.Data;

@Data
public class ShipmentStatusCountDto {
	
	private String shipmentStatus;
	private long customersCount;
	
	 public ShipmentStatusCountDto(String shipmentStatus, Long customersCount) {
	        this.shipmentStatus = shipmentStatus;
	        this.customersCount = customersCount;
	    }

}
