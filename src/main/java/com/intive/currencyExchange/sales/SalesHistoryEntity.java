package com.intive.currencyExchange.sales;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "sales_history")
public class SalesHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sold_Currency_Unit")
    private String soldCurrencyUnit;
    @Column(name = "currency_selling_rate_in_PLN")
    private Double currencySellingRateInPLN;
    @Column(name = "amount_of_currency_sold")
    private Integer amountOfCurrencySold;
    private Double rest;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "sold_date")
    private LocalDate soldDate;

    public SalesHistoryEntity(String soldCurrencyUnit, Double currencySellingRateInPLN, Integer amountOfCurrencySold, Double rest, LocalDate soldDate) {

        this.soldCurrencyUnit = soldCurrencyUnit;
        this.currencySellingRateInPLN = currencySellingRateInPLN;
        this.amountOfCurrencySold = amountOfCurrencySold;
        this.rest = rest;
        this.soldDate = soldDate;
    }

}
