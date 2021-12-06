package com.intive.currencyExchange.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/currency")
@RestController
class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/{code}")
    CurrencyEntity getCurrencyExchangeRate(@PathVariable String code) {
        return currencyExchangeService.getCurrency(code);
    }

    @GetMapping
    List<CurrencyEntity> getManyCurrencyExchangeRate(@RequestParam(required = false) List<String> codes) {
        if (codes == null) {
            return currencyExchangeService.getAll();
        } else {
            return currencyExchangeService.getCurrencies(codes);
        }
    }

}