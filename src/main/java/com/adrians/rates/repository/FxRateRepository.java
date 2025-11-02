package com.adrians.rates.repository;

import com.adrians.rates.entity.FxRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FxRateRepository extends JpaRepository<FxRateEntity, Long> {
    List<FxRateEntity> findByDate(LocalDate date);
}