package com.intive.currencyExchange.currency;

import lombok.Data;

import java.util.List;

@Data
public class Currency {

    private String currency;
    private String code;
    private List<Rates> rates;
}

