package com.adrians.rates.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fx_rates")
public class FxRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private double rate;
    private LocalDate date;

    public FxRateEntity() {}

    public FxRateEntity(String currency, double rate, LocalDate date) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() { return id; }
    public String getCurrency() { return currency; }
    public double getRate() { return rate; }
    public LocalDate getDate() { return date; }

    public void setCurrency(String currency) { this.currency = currency; }
    public void setRate(double rate) { this.rate = rate; }
    public void setDate(LocalDate date) { this.date = date; }
}