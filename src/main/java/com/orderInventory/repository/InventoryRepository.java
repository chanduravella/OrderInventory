package com.orderInventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderInventory.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory,Integer>{

}
