package com.dev.tradeflow.market.provider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.tradeflow.market.dto.MarketPriceResponse;
import com.dev.tradeflow.market.service.MarketDataProvider;

@Service
public class MockMarketProvider implements MarketDataProvider {

    @Override
    public MarketPriceResponse getPrice(String symbol) {

    	MarketPriceResponse response = new MarketPriceResponse();

    	response.setSymbol(symbol.toUpperCase());
    	response.setCompanyName("Mock Company");
    	response.setExchange("NSE");
    	response.setCurrentPrice(BigDecimal.valueOf(3450.75));
    	response.setPreviousClose(BigDecimal.valueOf(3425.10));
    	response.setDayHigh(BigDecimal.valueOf(3472.40));
    	response.setDayLow(BigDecimal.valueOf(3410.20));
    	response.setVolume(2456789L);
    	response.setLastUpdated(LocalDateTime.now());

    	return response;
    }

    @Override
    public List<MarketPriceResponse> getPrices(List<String> symbols) {

        return symbols.stream()
                .map(this::getPrice)
                .toList();
    }

    @Override
    public List<MarketPriceResponse> getAllMarketPrices() {

        return List.of(
                getPrice("TCS"),
                getPrice("INFY"),
                getPrice("RELIANCE")
        );
    }
}