## Documentation

###Launching the application
1. Download the application - clone the repository with the command: 
git clone https://github.com/Katarzyna-Zamorska/currency_exchange.git

2. Next in the root directory, select the command:
./mvnw spring-boot:run

3. I use H2 database and it is available at:

http://localhost:8080/console

4. I use Swagger UI and it is available at:

http://llocalhost:8080/swagger-ui.html



###Functionalities

My application enables:
1. Download the current currency sale rate from the National Bank of Poland, converted into PLN.
Then it writes the data to the database.

Sample request:

```html
http://localhost:8080/currency/usd
```

Sample response:
```json
{
  "id": 12,
  "currency": "dolar amerykański",
  "code": "USD",
  "currencySellingRateInPLN": 4.1083,
  "date": "06-12-2021"
}
```
2. Display the current exchange rate.


Sample request:

```html
http://localhost:8080/currency/usd
```

Sample response:
```json
{
  "id": 12,
  "currency": "dolar amerykański",
  "code": "USD",
  "currencySellingRateInPLN": 4.1083,
  "date": "06-12-2021"
}
```

3. Display the current rate for more currencies.

Sample request:

```html
http://localhost:8080/currency?codes=eur&codes=usd
```

Sample response:
```json
[
  {
    "id": 10,
    "currency": "euro",
    "code": "EUR",
    "currencySellingRateInPLN": 4.6398,
    "date": "06-12-2021"
  },
  {
    "id": 11,
    "currency": "dolar amerykański",
    "code": "USD",
    "currencySellingRateInPLN": 4.1083,
    "date": "06-12-2021"
  }
]
```

4. Saving the history of the course not longer than 7 days.
5. Conversion based on the amount for which the customer wants to buy the currency - it returns the number of units of the purchased currency and the rest.

Sample request:
```html
http://localhost:8080/sales/buy?amount=500&code=usd
```

Sample response:
```json
{
  "id": 6,
  "soldCurrencyUnit": "USD",
  "currencySellingRateInPLN": 4.1083,
  "amountOfCurrencySold": 121,
  "rest": 2.9,
  "soldDate": "06-12-2021"
}
```

6. Viewing sales history from the indicated date range.
   The date is set in the following format: dd-mm-yy.



Sample request:
```html
http://localhost:8080/sales?end=06-12-2021&start=05-12-2021
```

Sample response:
```json

[
  {
    "id": 1,
    "soldCurrencyUnit": "EUR",
    "currencySellingRateInPLN": 4.6405,
    "amountOfCurrencySold": 107,
    "rest": 3.47,
    "soldDate": "05-12-2021"
  },
  {
    "id": 2,
    "soldCurrencyUnit": "EUR",
    "currencySellingRateInPLN": 4.6405,
    "amountOfCurrencySold": 53,
    "rest": 4.05,
    "soldDate": "05-12-2021"
  },
  {
    "id": 3,
    "soldCurrencyUnit": "CHF",
    "currencySellingRateInPLN": 4.4537,
    "amountOfCurrencySold": 224,
    "rest": 2.37,
    "soldDate": "05-12-2021"
  },
  {
    "id": 4,
    "soldCurrencyUnit": "GBP",
    "currencySellingRateInPLN": 5.4502,
    "amountOfCurrencySold": 366,
    "rest": 5.23,
    "soldDate": "05-12-2021"
  },
  {
    "id": 5,
    "soldCurrencyUnit": "CAD",
    "currencySellingRateInPLN": 3.2137,
    "amountOfCurrencySold": 155,
    "rest": 1.88,
    "soldDate": "06-12-2021"
  }
]

```
