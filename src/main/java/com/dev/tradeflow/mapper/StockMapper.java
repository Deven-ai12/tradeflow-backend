package com.dev.tradeflow.mapper;

import org.springframework.stereotype.Component;

import com.dev.tradeflow.dto.request.CreateStockRequestDto;
import com.dev.tradeflow.dto.request.UpdateStockRequestDto;
import com.dev.tradeflow.dto.response.StockResponseDto;
import com.dev.tradeflow.entity.Stock;

@Component
public class StockMapper {

    public Stock toEntity(CreateStockRequestDto request) {

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

    public StockResponseDto toResponse(Stock stock) {

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
    
    public void updateEntity(Stock stock, UpdateStockRequestDto request) {

        stock.setSector(request.getSector());

        stock.setCurrentPrice(request.getCurrentPrice());
        stock.setPreviousClose(request.getPreviousClose());
        stock.setDayHigh(request.getDayHigh());
        stock.setDayLow(request.getDayLow());

        stock.setMarketCap(request.getMarketCap());
        stock.setVolume(request.getVolume());
    }
}