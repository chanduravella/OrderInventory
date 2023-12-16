package com.orderInventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orderInventory.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
	
	@Query(nativeQuery = true, value="SELECT * from products ORDER BY :field :sortBy")
	List<Products> findAllProductsByField(@Param("field")String field, @Param("sortBy")String sortBy);

}
