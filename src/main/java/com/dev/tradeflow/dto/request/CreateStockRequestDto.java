package com.dev.tradeflow.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStockRequestDto {

    @NotBlank(message = "Symbol is required.")
    private String symbol;

    @NotBlank(message = "Company name is required.")
    private String companyName;

    @NotBlank(message = "Exchange is required.")
    private String exchange;

    private String sector;

    @NotNull(message = "Current price is required.")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal currentPrice;

    private BigDecimal previousClose;

    private BigDecimal dayHigh;

    private BigDecimal dayLow;

    private BigDecimal marketCap;

    private Long volume;

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
    
}