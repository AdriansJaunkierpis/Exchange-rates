package com.adrians.rates.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fx_rates")
public class FxRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private BigDecimal rate;
    private LocalDate date;

    public FxRateEntity() {}

    public FxRateEntity(String currency, BigDecimal rate, LocalDate date) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() { return id; }
    public String getCurrency() { return currency; }
    public BigDecimal getRate() { return rate; }
    public LocalDate getDate() { return date; }

    public void setCurrency(String currency) { this.currency = currency; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
    public void setDate(LocalDate date) { this.date = date; }
}