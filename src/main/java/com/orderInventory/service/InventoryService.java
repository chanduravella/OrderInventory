package com.orderInventory.service;

import java.util.List;

import com.orderInventory.dto.InventoryDto;
import com.orderInventory.exception.ResourceNotFoundException;

public interface InventoryService {
	
	List<InventoryDto> getInventoryByStoreId(int storeId) throws ResourceNotFoundException;

}
