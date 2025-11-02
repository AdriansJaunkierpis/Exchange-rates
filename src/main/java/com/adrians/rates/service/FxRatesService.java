package com.adrians.rates.service;

import com.adrians.rates.entity.FxRateEntity;
import com.adrians.rates.model.FxRates;
import com.adrians.rates.repository.FxRateRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FxRatesService {
    private static final String CURRENT_RATES_URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
    private static final String PAST_RATES_URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRates?tp=EU&dt=";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final FxRateRepository fxRateRepository;
    private final RestClient restClient;
    private final XmlMapper xmlMapper;

    public FxRatesService(FxRateRepository fxRateRepository) {
        this.fxRateRepository = fxRateRepository;
        this.restClient = RestClient.builder().build();
        this.xmlMapper = new XmlMapper();
    }

    @PostConstruct
    public void initializeRates() {
        if (fxRateRepository.count() == 0) {
            populatePastRates(90);
        }
    }

    public void populatePastRates(int days) {

        LocalDate today = LocalDate.now();
        for (int i = 0; i < days; i++) {
            LocalDate date = today.minusDays(i);
            try {
                fetchAndSaveRatesForDate(date, PAST_RATES_URL + date.format(DATE_FORMATTER));
            } catch (Exception e) {
                throw new RuntimeException("Failed to get exchange rates: " + e.getMessage(), e);
            }
        }
        System.out.println("Finished populating FX rates for the last 90 days.");
    }

    public List<FxRateEntity> getCurrentFxRates() {
        LocalDate today = LocalDate.now();

        List<FxRateEntity> existingRates = fxRateRepository.findByDate(today);
        if (!existingRates.isEmpty()) {
            return existingRates;
        }

        try {
            return fetchAndSaveRatesForDate(today, CURRENT_RATES_URL);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get exchange rates: " + e.getMessage(), e);
        }
    }

    private List<FxRateEntity> fetchAndSaveRatesForDate(LocalDate date, String url) throws Exception {

        String xmlResponse = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);

        FxRates fxRates = xmlMapper.readValue(xmlResponse, FxRates.class);

        List<FxRateEntity> entities = fxRates.getFxRates().stream()
                .flatMap(fxRate -> fxRate.getCcyAmts().stream())
                .filter(ccyAmt -> !"EUR".equals(ccyAmt.getCurrency()))
                .map(ccyAmt -> new FxRateEntity(
                        ccyAmt.getCurrency(),
                        Double.parseDouble(ccyAmt.getAmount()),
                        date
                ))
                .collect(Collectors.toList());

        return fxRateRepository.saveAll(entities);
    }
}