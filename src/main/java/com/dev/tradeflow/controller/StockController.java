package com.dev.tradeflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.tradeflow.dto.request.CreateStockRequestDto;
import com.dev.tradeflow.dto.response.StockResponseDto;
import com.dev.tradeflow.service.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	private StockService stockService;
	
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@PostMapping
	public ResponseEntity<StockResponseDto> createStock(
	        @Valid @RequestBody CreateStockRequestDto request) {

	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(stockService.createStock(request));
	}
}
