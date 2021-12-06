package com.intive.currencyExchange.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface CurrencyExchangeRepository extends JpaRepository<CurrencyEntity, Integer> {

    List<CurrencyEntity> findByDate(LocalDate date);
}
