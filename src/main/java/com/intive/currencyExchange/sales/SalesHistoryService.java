package com.intive.currencyExchange.sales;

import com.intive.currencyExchange.nbpApi.GetCurrencyRateBasedOnAmountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class SalesHistoryService {

    @Autowired
    SalesHistoryService(SalesHistoryRepository salesHistoryRepository, GetCurrencyRateBasedOnAmountUseCase getCurrencyRateBasedOnAmountUseCase) {
        this.salesHistoryRepository = salesHistoryRepository;
        this.getCurrencyRateBasedOnAmountUseCase = getCurrencyRateBasedOnAmountUseCase;
    }

    private final SalesHistoryRepository salesHistoryRepository;
    private final GetCurrencyRateBasedOnAmountUseCase getCurrencyRateBasedOnAmountUseCase;


    SalesHistoryEntity createSalesHistory(Double amount, String code) {
        SalesHistoryEntity salesHistoryEntity = getCurrencyRateBasedOnAmountUseCase.convertingExchangeRateBasedOnAnAmount(amount, code);
        return salesHistoryRepository.save(salesHistoryEntity);

    }
    
    List<SalesHistoryEntity> getSalesHistoryByDate(LocalDate start, LocalDate end) {
        return salesHistoryRepository.findBySoldDateGreaterThanEqualAndSoldDateLessThanEqual(start, end);
    }
}
