package com.orderInventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orderInventory.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login,String>{
	

}
