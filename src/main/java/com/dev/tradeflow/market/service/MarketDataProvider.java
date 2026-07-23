package com.dev.tradeflow.market.service;

import java.util.List;

import com.dev.tradeflow.market.dto.MarketPriceResponse;

public interface MarketDataProvider {

    MarketPriceResponse getPrice(String symbol);

    List<MarketPriceResponse> getPrices(List<String> symbols);

    List<MarketPriceResponse> getAllMarketPrices();
}