package com.adrians.rates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FxRateResponse {
    private String currency;
    private BigDecimal rate;
    private LocalDate date;

    public FxRateResponse(String currency, BigDecimal rate, LocalDate date) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public LocalDate getDate() {
        return date;
    }
}
