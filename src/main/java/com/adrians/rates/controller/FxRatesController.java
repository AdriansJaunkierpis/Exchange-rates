package com.adrians.rates.controller;

import com.adrians.rates.dto.FxRateResponse;
import com.adrians.rates.entity.FxRateEntity;
import com.adrians.rates.model.FxRates;
import com.adrians.rates.service.FxRatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<FxRateResponse>> getCurrentFxRates() {
        List<FxRateResponse> rates = fxRatesService.getCurrentFxRates();
        return ResponseEntity.ok(rates);
    }

    @GetMapping("/{currency}/history")
    public ResponseEntity<List<FxRateResponse>> getCurrencyHistory(@PathVariable String currency) {
        List<FxRateResponse> history = fxRatesService.getFxRatesForCurrency(currency);
        return ResponseEntity.ok(history);
    }
}