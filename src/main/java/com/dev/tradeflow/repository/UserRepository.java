package com.dev.tradeflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.tradeflow.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	 boolean existsByEmail(String email);
}
