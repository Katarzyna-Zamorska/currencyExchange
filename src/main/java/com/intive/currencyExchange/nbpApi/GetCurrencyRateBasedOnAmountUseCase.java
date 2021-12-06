package com.intive.currencyExchange.nbpApi;

import com.intive.currencyExchange.currency.Currency;
import com.intive.currencyExchange.sales.SalesHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GetCurrencyRateBasedOnAmountUseCase {
    private final FetchSellingRateUseCase fetchSellingRateUseCase;

    @Autowired
    GetCurrencyRateBasedOnAmountUseCase(FetchSellingRateUseCase fetchSellingRateUseCase) {
        this.fetchSellingRateUseCase = fetchSellingRateUseCase;
    }

    public SalesHistoryEntity convertingExchangeRateBasedOnAnAmount(Double amount, String code) {
        FetchSellingRateUseCase.Result fetchSellingRateResult = fetchSellingRateUseCase.fetchSellingRate(code);
        Currency exchangeRate = fetchSellingRateResult.exchangeRate();
        double actualCurrencySellingRateValue = fetchSellingRateResult.actualCurrencySellingRateValue();

        int amountOfCurrencySold = (int) (amount / actualCurrencySellingRateValue);
        double result = amount - (amountOfCurrencySold * actualCurrencySellingRateValue);
        double rest = Math.round(result * 100.0) / 100.0;

        SalesHistoryEntity salesHistoryEntity;
        salesHistoryEntity = new SalesHistoryEntity(exchangeRate.getCode(), actualCurrencySellingRateValue, amountOfCurrencySold,
                rest, LocalDate.now());
        return salesHistoryEntity;
    }
}
