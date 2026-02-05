package com.adrians.rates;

import com.adrians.rates.dto.FxRateResponse;
import com.adrians.rates.entity.FxRateEntity;
import com.adrians.rates.repository.FxRateRepository;
import com.adrians.rates.service.FxRatesService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FxRatesServiceTests {

    @Mock
    FxRateRepository repository;
    @Mock
    RestClient restClient;
    @Mock
    XmlMapper xmlMapper;
    @InjectMocks
    FxRatesService service;

    @Test
    void shouldReturnExistingRatesWithoutCallingExternalApi() {
        LocalDate today = LocalDate.now();
        List<FxRateEntity> existing = List.of(new FxRateEntity("USD", new BigDecimal("1.10"), today));

        when(repository.findByDate(today)).thenReturn(existing);

        List<FxRateResponse> result = service.getCurrentFxRates();
        assertThat(result).hasSize(1);
        verifyNoInteractions(restClient);
    }

}
