package com.dev.tradeflow.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stocks")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Example: RELIANCE, TCS, INFY
    @Column(nullable = false, unique = true, length = 20)
    private String symbol;

    // Example: Reliance Industries Ltd.
    @Column(nullable = false, length = 200)
    private String companyName;

    // NSE / BSE
    @Column(nullable = false, length = 20)
    private String exchange;

    // Banking, IT, Pharma...
    @Column(length = 100)
    private String sector;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal currentPrice;

    @Column(precision = 15, scale = 2)
    private BigDecimal previousClose;

    @Column(precision = 15, scale = 2)
    private BigDecimal dayHigh;

    @Column(precision = 15, scale = 2)
    private BigDecimal dayLow;

    @Column(precision = 20, scale = 2)
    private BigDecimal marketCap;

    @Column
    private Long volume;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    @PrePersist
    public void onCreate() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

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