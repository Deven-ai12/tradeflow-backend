package com.dev.tradeflow.market.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.tradeflow.market.dto.MarketPriceResponse;
import com.dev.tradeflow.market.service.MarketDataProvider;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketDataProvider marketDataProvider;

    public MarketController(MarketDataProvider marketDataProvider) {
        this.marketDataProvider = marketDataProvider;
    }

    @GetMapping("/{symbol}")
    public MarketPriceResponse getPrice(
            @PathVariable String symbol) {

        return marketDataProvider.getPrice(symbol);
    }

    @GetMapping
    public List<MarketPriceResponse> getAllPrices() {

        return marketDataProvider.getAllMarketPrices();
    }
}