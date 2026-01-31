package com.adrians.rates.schedule;

import com.adrians.rates.service.FxRatesService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FxRatesScheduler {
    private final FxRatesService fxRatesService;

    public FxRatesScheduler(FxRatesService fxRatesService) {
        this.fxRatesService = fxRatesService;
    }

    // Every day at 03:00 AM
    @Scheduled(cron = "0 0 3 * * ?")
    public void fetchDailyRates() {
        fxRatesService.getCurrentFxRates();
    }
}