package com.intive.currencyExchange.currency;

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
@Table(name = "currency_exchange")
class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "currency_name")
    private String currency;
    @Column(name = "currency_code")
    private String code;
    @Column(name = "currency_selling_rate_in_PLN")
    private Double currencySellingRateInPLN;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "created_date")
    private LocalDate date;

    public CurrencyEntity(String currency, String code, Double bindBuyOnPLN, LocalDate date) {
        this.currency = currency;
        this.code = code;
        this.currencySellingRateInPLN = bindBuyOnPLN;
        this.date = date;
    }
}
