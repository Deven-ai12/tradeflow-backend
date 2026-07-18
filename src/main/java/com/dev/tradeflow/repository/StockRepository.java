package com.dev.tradeflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.tradeflow.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
	
	Optional<Stock> findBySymbol(String symbol);

	boolean existsBySymbol(String symbol);
	
	void deleteBySymbol(String symbol);
}
