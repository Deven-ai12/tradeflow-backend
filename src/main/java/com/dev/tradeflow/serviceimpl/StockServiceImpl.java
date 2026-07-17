package com.dev.tradeflow.serviceimpl;

import org.springframework.stereotype.Service;

import com.dev.tradeflow.dto.request.CreateStockRequestDto;
import com.dev.tradeflow.dto.response.StockResponseDto;
import com.dev.tradeflow.entity.Stock;
import com.dev.tradeflow.repository.StockRepository;
import com.dev.tradeflow.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	private final StockRepository stockRepository;
	
	
	public StockServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}


	@Override
	public StockResponseDto createStock(CreateStockRequestDto request) {

	    if (stockRepository.existsBySymbol(request.getSymbol())) {
	        throw new RuntimeException(
	                "Stock already exists with symbol: " + request.getSymbol());
	    }

	    Stock stock = mapToEntity(request);

	    Stock savedStock = stockRepository.save(stock);

	    return mapToResponse(savedStock);
	}
	
	
	
	
	
	
	
	
	
	
	
//	---------------> map methods------------------------->
	
	private Stock mapToEntity(CreateStockRequestDto request) {

	    Stock stock = new Stock();

	    stock.setSymbol(request.getSymbol().toUpperCase());
	    stock.setCompanyName(request.getCompanyName());
	    stock.setExchange(request.getExchange());
	    stock.setSector(request.getSector());

	    stock.setCurrentPrice(request.getCurrentPrice());
	    stock.setPreviousClose(request.getPreviousClose());
	    stock.setDayHigh(request.getDayHigh());
	    stock.setDayLow(request.getDayLow());

	    stock.setMarketCap(request.getMarketCap());
	    stock.setVolume(request.getVolume());

	    return stock;
	}
	
	
	private StockResponseDto mapToResponse(Stock stock) {

	    StockResponseDto response = new StockResponseDto();

	    response.setId(stock.getId());
	    response.setSymbol(stock.getSymbol());
	    response.setCompanyName(stock.getCompanyName());
	    response.setExchange(stock.getExchange());
	    response.setSector(stock.getSector());

	    response.setCurrentPrice(stock.getCurrentPrice());
	    response.setPreviousClose(stock.getPreviousClose());
	    response.setDayHigh(stock.getDayHigh());
	    response.setDayLow(stock.getDayLow());

	    response.setMarketCap(stock.getMarketCap());
	    response.setVolume(stock.getVolume());

	    response.setLastUpdated(stock.getLastUpdated());

	    return response;
	}
}
