package com.dev.tradeflow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.tradeflow.dto.request.CreateStockRequestDto;
import com.dev.tradeflow.dto.request.UpdateStockRequestDto;
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
	public ResponseEntity<StockResponseDto> createStock(@Valid @RequestBody CreateStockRequestDto request) {

		return ResponseEntity.status(HttpStatus.CREATED).body(stockService.createStock(request));
	}

	@GetMapping
	public ResponseEntity<List<StockResponseDto>> getAllStocks() {

		List<StockResponseDto> stocks = stockService.getAllStocks();

		return ResponseEntity.ok(stocks);
	}

	@GetMapping("/{symbol}")
	public ResponseEntity<StockResponseDto> getStockBySymbol(@PathVariable String symbol) {

		return ResponseEntity.ok(stockService.getStockBySymbol(symbol));
	}

	@PutMapping("/{symbol}")
	public ResponseEntity<StockResponseDto> updateStock(@PathVariable String symbol,
			@Valid @RequestBody UpdateStockRequestDto request) {

		return ResponseEntity.ok(stockService.updateStock(symbol, request));
	}

	@DeleteMapping("/{symbol}")
	public ResponseEntity<Void> deleteStock(@PathVariable String symbol) {

		stockService.deleteStock(symbol);

		return ResponseEntity.noContent().build();
	}
}
