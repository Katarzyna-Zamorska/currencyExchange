package com.intive.currencyExchange.nbpApi;

import com.intive.currencyExchange.currency.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
class GetCurrencyExchangeByCurrencyCodeAction {
    private final RestTemplate restTemplate;

    @Autowired
    public GetCurrencyExchangeByCurrencyCodeAction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Currency getExchangeRate(String code) {

        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/c/%s/?format=json", code);

        return restTemplate.getForObject(url,
                Currency.class);
    }

}
