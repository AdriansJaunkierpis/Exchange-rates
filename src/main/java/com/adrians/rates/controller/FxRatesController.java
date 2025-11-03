package com.adrians.rates.controller;

import com.adrians.rates.entity.FxRateEntity;
import com.adrians.rates.model.FxRates;
import com.adrians.rates.service.FxRatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
@CrossOrigin(origins = "http://localhost:4200")
public class FxRatesController {

    private final FxRatesService fxRatesService;

    public FxRatesController(FxRatesService fxRatesService) {
        this.fxRatesService = fxRatesService;
    }

    @GetMapping
    public ResponseEntity<List<FxRateEntity>> getCurrentFxRates() {
        List<FxRateEntity> rates = fxRatesService.getCurrentFxRates();
        return ResponseEntity.ok(rates);
    }
}