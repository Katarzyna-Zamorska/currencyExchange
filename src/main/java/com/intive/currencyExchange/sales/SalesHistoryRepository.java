package com.intive.currencyExchange.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface SalesHistoryRepository extends JpaRepository<SalesHistoryEntity, Integer> {

    List<SalesHistoryEntity> findBySoldDateGreaterThanEqualAndSoldDateLessThanEqual(LocalDate start, LocalDate end);

}


