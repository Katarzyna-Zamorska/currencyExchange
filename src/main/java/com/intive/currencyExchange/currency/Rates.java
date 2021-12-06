package com.intive.currencyExchange.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rates {

    private String effectiveDate;
    @JsonProperty("ask")
    private Double currencySellingRateInPLN;
}
