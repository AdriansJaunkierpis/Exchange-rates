package com.adrians.rates;

import com.adrians.rates.entity.FxRateEntity;
import com.adrians.rates.repository.FxRateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class FxRateRepositoryTests {

    @Autowired
    FxRateRepository repository;

    @Test
    void shouldFindRatesByCurrencyAndDateRange() {
        LocalDate now = LocalDate.now();

        repository.save(new FxRateEntity("USD", new BigDecimal("1.10"), now));
        repository.save(new FxRateEntity("USD", new BigDecimal("1.10"), now.minusDays(1)));

        List<FxRateEntity> result = repository.findByCurrencyAndDateBetweenOrderByDateDesc("USD", now.minusDays(2), now);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDate()).isAfter(result.get(1).getDate());
    }
}
