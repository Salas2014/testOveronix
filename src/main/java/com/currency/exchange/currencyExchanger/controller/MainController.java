package com.currency.exchange.currencyExchanger.controller;

import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.HistoryJsonBlock;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.JsonBlock;

import com.currency.exchange.currencyExchanger.service.CurrentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/v1/currency")
public class MainController {

   @Autowired
   private CurrentServiceImpl currentService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonBlock> getDate(@RequestParam(required = false) String baseCurrency){
        return new ResponseEntity<>(
                currentService.getValue(baseCurrency), HttpStatus.OK);
    }

    @GetMapping(path = "/history",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoryJsonBlock> testMethod(@RequestParam(required = false) String baseCurrency,
                                                       String dateFrom, String dateTo) {
        return new ResponseEntity<>(
                currentService.getHistoryExchange(baseCurrency, dateFrom, dateTo), HttpStatus.OK);
    }

    @GetMapping(path = "/bestRate")
    public ResponseEntity<Double> getBestRate(@RequestParam String baseCode,
                              @RequestParam String targetCode){
       return new ResponseEntity<>(currentService.selectBEstRate(baseCode,targetCode), HttpStatus.OK);
    }

    @GetMapping(path = "/codes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllCodes(){
        return new ResponseEntity<>(currentService.getCurrencyCode(), HttpStatus.OK);
    }

}


