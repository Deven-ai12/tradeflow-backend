package com.dev.tradeflow.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponseDto {

    private Long id;

    private String symbol;

    private String companyName;

    private String exchange;

    private String sector;

    private BigDecimal currentPrice;

    private BigDecimal previousClose;

    private BigDecimal dayHigh;

    private BigDecimal dayLow;

    private BigDecimal marketCap;

    private Long volume;

    private LocalDateTime lastUpdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}

	public BigDecimal getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(BigDecimal dayHigh) {
		this.dayHigh = dayHigh;
	}

	public BigDecimal getDayLow() {
		return dayLow;
	}

	public void setDayLow(BigDecimal dayLow) {
		this.dayLow = dayLow;
	}

	public BigDecimal getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
    
}