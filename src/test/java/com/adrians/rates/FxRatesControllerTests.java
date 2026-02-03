package com.adrians.rates;

import com.adrians.rates.controller.FxRatesController;
import com.adrians.rates.entity.FxRateEntity;
import com.adrians.rates.service.FxRatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FxRatesController.class)
public class FxRatesControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    FxRatesService fxRatesService;

    @Test
    void shouldReturnCurrentRates() throws Exception {
        LocalDate now = LocalDate.now();

        List<FxRateEntity> rates = List.of(new FxRateEntity("USD", new BigDecimal("1.08"), now));

        when(fxRatesService.getCurrentFxRates()).thenReturn(rates);

        mockMvc.perform(get("/api/rates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].currency").value("USD"))
                .andExpect(jsonPath("$[0].rate").value(1.08));
    }


}
