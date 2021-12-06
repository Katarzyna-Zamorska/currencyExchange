package com.intive.currencyExchange.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/sales")
@RestController
class SalesHistoryController {

    private final SalesHistoryService salesHistoryService;

    @Autowired
    SalesHistoryController(SalesHistoryService salesHistoryService) {
        this.salesHistoryService = salesHistoryService;
    }

    @GetMapping("/buy")
    SalesHistoryEntity getSalesHistoryEntity(@RequestParam Double amount, @RequestParam String code) {
        return salesHistoryService.createSalesHistory(amount, code);
    }

    @GetMapping
    List<SalesHistoryEntity> getSalesHistoryByDate(@RequestParam
                                                   @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                           LocalDate start,
                                                   @RequestParam
                                                   @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                           LocalDate end) {
        return salesHistoryService.getSalesHistoryByDate(start, end);
    }
}
