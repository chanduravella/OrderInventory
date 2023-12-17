package com.orderInventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderInventory.dto.InventoryDto;
import com.orderInventory.entity.Inventory;
import com.orderInventory.entity.Orders;
import com.orderInventory.entity.Products;
import com.orderInventory.entity.Stores;
import com.orderInventory.exception.ResourceNotFoundException;
import com.orderInventory.repository.InventoryRepository;
import com.orderInventory.repository.OrdersRepository;
import com.orderInventory.repository.ProductsRepository;
import com.orderInventory.repository.StoresRepository;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	InventoryRepository inventoryRepository;
	@Autowired
	OrdersRepository ordersRepository;

	@Override
	public List<InventoryDto> getInventoryByStoreId(int storeId) throws ResourceNotFoundException{
		
		List<Inventory> inventoryList = inventoryRepository.findByStoreId_storeId(storeId);
		
		if(inventoryList.isEmpty()) {
			
			throw new ResourceNotFoundException("No Inventory found for store with Id: "+storeId);
			
		}		

        return inventoryList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private InventoryDto mapToDto(Inventory inventory) {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryId(inventory.getInventoryId());
        inventoryDto.setStoreId(inventory.getStoreId().getStoreId());
        inventoryDto.setProductId(inventory.getProductId().getProductId());
        inventoryDto.setProductInventory(inventory.getProductInventory());

        // Fetch additional details from other repositories
        inventoryDto.setStoreName(inventory.getStoreId().getStoreName());
        inventoryDto.setProductName(inventory.getProductId().getProductName());
        inventoryDto.setUnitPrice(inventory.getProductId().getUnitPrice());
        inventoryDto.setColour(inventory.getProductId().getColour());
        inventoryDto.setBrand(inventory.getProductId().getBrand());
        inventoryDto.setSize(inventory.getProductId().getSize());
        inventoryDto.setRating(inventory.getProductId().getRating());

        // Fetch order status from Orders repository
        
        List<Orders> ordersList = ordersRepository.findByStoreId_StoreId(inventory.getStoreId().getStoreId());

     
     if (!ordersList.isEmpty()) {
         inventoryDto.setOrderStatus(ordersList.get(0).getOrderStatus());
     } else {
         inventoryDto.setOrderStatus(null);
     }

        return inventoryDto;
    }
		
		
	
	
	}
