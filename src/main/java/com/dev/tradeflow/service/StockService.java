package com.dev.tradeflow.service;

import java.util.List;


import com.dev.tradeflow.dto.request.CreateStockRequestDto;
import com.dev.tradeflow.dto.request.UpdateStockRequestDto;
import com.dev.tradeflow.dto.response.StockResponseDto;

public interface StockService {

    StockResponseDto createStock(CreateStockRequestDto request);

    List<StockResponseDto> getAllStocks();

    StockResponseDto getStockBySymbol(String symbol);

    StockResponseDto updateStock(String symbol, UpdateStockRequestDto request);

    void deleteStock(String symbol);
}