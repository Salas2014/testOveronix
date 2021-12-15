package com.currency.exchange.currencyExchanger.controller;

import com.currency.exchange.currencyExchanger.entity.InfoResponse;
import com.currency.exchange.currencyExchanger.service.CurrencyServiceImpl;
import com.currency.exchange.currencyExchanger.service.CurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/currency")
public class MainController {

    CurrentService currentService;

    public MainController(CurrentService currentService) {
        this.currentService = currentService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoResponse> getDate(@RequestParam(required = false)
                                                            String base_currency){

        return new ResponseEntity<>(currentService.getValue(base_currency), HttpStatus.OK);
    }

    @GetMapping(path = "/codes",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCurrentCodeValue(@RequestParam(required = false)
                                                                  String tag){
        return new ResponseEntity<>(currentService.getCurrencyCode(tag), HttpStatus.OK);
    }
}
