package com.adrians.rates.service;

import com.adrians.rates.entity.FxRateEntity;
import com.adrians.rates.model.FxRates;
import com.adrians.rates.repository.FxRateRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FxRatesService {
    private static final String API_URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";

    private final FxRateRepository fxRateRepository;
    private final RestClient restClient;
    private final XmlMapper xmlMapper;

    public FxRatesService(FxRateRepository fxRateRepository) {
        this.fxRateRepository = fxRateRepository;
        this.restClient = RestClient.builder().build();
        this.xmlMapper = new XmlMapper();
    }

    public List<FxRateEntity> getCurrentFxRates() {
        LocalDate today = LocalDate.now();

        List<FxRateEntity> existingRates = fxRateRepository.findByDate(today);
        if (!existingRates.isEmpty()) {
            return existingRates;
        }

        try {
            String xmlResponse = restClient.get()
                    .uri(API_URL)
                    .retrieve()
                    .body(String.class);

            FxRates fxRates = xmlMapper.readValue(xmlResponse, FxRates.class);

            List<FxRateEntity> entities = fxRates.getFxRates().stream()
                    .flatMap(fxRate -> fxRate.getCcyAmts().stream())
                    .filter(ccyAmt -> !"EUR".equals(ccyAmt.getCurrency()))
                    .map(ccyAmt -> new FxRateEntity(
                            ccyAmt.getCurrency(),
                            Double.parseDouble(ccyAmt.getAmount()),
                            today
                    ))
                    .collect(Collectors.toList());

            return fxRateRepository.saveAll(entities);

        } catch (Exception e) {
            throw new RuntimeException("Failed to get exchange rates: " + e.getMessage(), e);
        }
    }
}