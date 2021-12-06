package com.intive.currencyExchange.currency;

import com.intive.currencyExchange.nbpApi.FetchSellingRateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
class CurrencyExchangeService {

    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final FetchSellingRateUseCase fetchSellingRateUseCase;

    @Autowired
    CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository, FetchSellingRateUseCase fetchSellingRateUseCase) {
        this.currencyExchangeRepository = currencyExchangeRepository;
        this.fetchSellingRateUseCase = fetchSellingRateUseCase;
    }


    CurrencyEntity getCurrency(String code) {
        FetchSellingRateUseCase.Result result = fetchSellingRateUseCase.fetchSellingRate(code);
        Currency exchangeRate = result.exchangeRate();
        double actualCurrencySellingRateValue = result.actualCurrencySellingRateValue();

        CurrencyEntity currency;
        currency = new CurrencyEntity(exchangeRate.getCurrency(), exchangeRate.getCode(),
                actualCurrencySellingRateValue, LocalDate.now());
        return currencyExchangeRepository.save(currency);
    }

    List<CurrencyEntity> getCurrencies(List<String> codes) {
        return codes.stream()
                .map(this::getCurrency)
                .collect(Collectors.toList());
    }

    List<CurrencyEntity> getAll() {
        LocalDate startDate = LocalDate.now().minusDays(7);
        List<CurrencyEntity> currencyToDelete = currencyExchangeRepository.findByDate(startDate);
        currencyExchangeRepository.deleteAll(currencyToDelete);
        return currencyExchangeRepository.findAll();
    }
}

