package com.orderInventory.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orderInventory.dto.OrderStatusCountDto;
import com.orderInventory.entity.Inventory;
import com.orderInventory.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository <Orders,Integer>{
	
	@Query(nativeQuery = true, 
			value = "SELECT order_status, COUNT(customer_id) as customerCount FROM orders GROUP BY order_status")
	List<Object[]> getOrderStatusCount();
	
	List<Orders> findByStoreId_StoreName(String store);

	List<Orders> findByStoreId_StoreId(int storeId);
	
	
	

}
