package com.intive.currencyExchange.nbpApi;

import com.intive.currencyExchange.currency.Currency;
import com.intive.currencyExchange.currency.Rates;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class FetchSellingRateUseCase {

    private final GetCurrencyExchangeByCurrencyCodeAction getCurrencyExchangeByCurrencyCode;

    public FetchSellingRateUseCase(GetCurrencyExchangeByCurrencyCodeAction getCurrencyExchangeByCurrencyCode) {
        this.getCurrencyExchangeByCurrencyCode = getCurrencyExchangeByCurrencyCode;
    }

    public Result fetchSellingRate(String code) {
        Currency exchangeRate = getCurrencyExchangeByCurrencyCode.getExchangeRate(code);
        if (exchangeRate.getCurrency() == null || exchangeRate.getCode() == null) {
            throw new NoSuchElementException("Missing exchange rate params!");
        }
        List<Double> actualCurrencySellingRate = exchangeRate.getRates().stream()
                .map((Rates::getCurrencySellingRateInPLN))
                .collect(Collectors.toList());
        double actualCurrencySellingRateValue = actualCurrencySellingRate.stream().findFirst().orElseThrow(() -> {
            throw new NoSuchElementException("No exchange rate");
        });
        return new Result(exchangeRate, actualCurrencySellingRateValue);
    }

    public static record Result(Currency exchangeRate, double actualCurrencySellingRateValue) {
    }
}
