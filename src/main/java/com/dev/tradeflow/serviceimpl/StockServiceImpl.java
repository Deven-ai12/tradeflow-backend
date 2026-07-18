package com.dev.tradeflow.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.tradeflow.dto.request.CreateStockRequestDto;
import com.dev.tradeflow.dto.request.UpdateStockRequestDto;
import com.dev.tradeflow.dto.response.StockResponseDto;
import com.dev.tradeflow.entity.Stock;
import com.dev.tradeflow.exception.StockNotFoundException;
import com.dev.tradeflow.mapper.StockMapper;
import com.dev.tradeflow.repository.StockRepository;
import com.dev.tradeflow.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	private final StockRepository stockRepository;
	private final StockMapper stockMapper;
	
	
	public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
		this.stockRepository = stockRepository;
		this.stockMapper = stockMapper;
	}


	@Override
	public StockResponseDto createStock(CreateStockRequestDto request) {

	    if (stockRepository.existsBySymbol(request.getSymbol())) {
	        throw new RuntimeException(
	                "Stock already exists with symbol: " + request.getSymbol());
	    }

	    Stock stock = stockMapper.toEntity(request);

	    Stock savedStock = stockRepository.save(stock);

	    return stockMapper.toResponse(savedStock);
	}
	
	@Override
	public List<StockResponseDto> getAllStocks() {

	    List<Stock> stocks = stockRepository.findAll();

	    return stocks.stream()
	            .map(stockMapper::toResponse)
	            .toList();
	}
	
	
	@Override
	public StockResponseDto getStockBySymbol(String symbol) {

	    Stock stock = stockRepository.findBySymbol(symbol.toUpperCase())
	            .orElseThrow(() ->
	                    new StockNotFoundException(
	                            "Stock with symbol " + symbol + " not found."));

	    return stockMapper.toResponse(stock);
	}
	
	@Override
	public StockResponseDto updateStock(String symbol,
	                                 UpdateStockRequestDto request) {

	    Stock stock = stockRepository.findBySymbol(symbol.toUpperCase())
	            .orElseThrow(() ->
	                    new StockNotFoundException(
	                            "Stock with symbol " + symbol + " not found."));

	    stockMapper.updateEntity(stock, request);

	    Stock updatedStock = stockRepository.save(stock);

	    return stockMapper.toResponse(updatedStock);
	}
	
	@Override
	public void deleteStock(String symbol) {

	    Stock stock = stockRepository.findBySymbol(symbol.toUpperCase())
	            .orElseThrow(() ->
	                    new StockNotFoundException(
	                            "Stock with symbol " + symbol + " not found."));

	    stockRepository.delete(stock);
	}
}
