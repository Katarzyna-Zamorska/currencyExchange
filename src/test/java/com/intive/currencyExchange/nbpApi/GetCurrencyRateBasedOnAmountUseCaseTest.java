package com.intive.currencyExchange.nbpApi;

import com.intive.currencyExchange.currency.Currency;
import com.intive.currencyExchange.sales.SalesHistoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetCurrencyRateBasedOnAmountUseCaseTest {

    @Mock
    FetchSellingRateUseCase fetchSellingRateUseCase;

    @Test

    void when_user_want_to_buy_USD_for_PLN_200_then_49_20_USD_should_be_returned_with_1_06_PLN_rest() {
        //given
        GetCurrencyRateBasedOnAmountUseCase getCurrencyRateBasedOnAmountUseCase = new GetCurrencyRateBasedOnAmountUseCase(fetchSellingRateUseCase);
        Currency currency = new Currency();
        currency.setCode("USD");
        Mockito.when(fetchSellingRateUseCase.fetchSellingRate("USD"))
                .thenReturn(new FetchSellingRateUseCase.Result(currency, 4.06));

        //when
        SalesHistoryEntity salesHistoryEntity = getCurrencyRateBasedOnAmountUseCase.convertingExchangeRateBasedOnAnAmount(200.0, "USD");

        //then
        assertEquals(49, salesHistoryEntity.getAmountOfCurrencySold());
        assertEquals(1.06, salesHistoryEntity.getRest());
    }

}