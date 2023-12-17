package com.orderInventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderInventory.entity.Inventory;
import com.orderInventory.entity.Stores;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory,Integer>{
	
	List<Inventory> findByStoreId_storeId(int storeId);

}
