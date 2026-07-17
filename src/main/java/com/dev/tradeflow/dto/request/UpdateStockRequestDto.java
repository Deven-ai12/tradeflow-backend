package com.dev.tradeflow.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockRequestDto {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal currentPrice;

    private BigDecimal previousClose;

    private BigDecimal dayHigh;

    private BigDecimal dayLow;

    private BigDecimal marketCap;

    private Long volume;

    private String sector;

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

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
    
}