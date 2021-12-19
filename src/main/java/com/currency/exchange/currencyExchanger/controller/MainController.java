package com.currency.exchange.currencyExchanger.controller;

import com.currency.exchange.currencyExchanger.convertor.MyDTOJsonConvertor;
import com.currency.exchange.currencyExchanger.dto.JsonBlock;
import com.currency.exchange.currencyExchanger.entity.InfoResponse;
import com.currency.exchange.currencyExchanger.entity.forConvertor.TestEntityJson;
import com.currency.exchange.currencyExchanger.repository.FreeCurrencyApiRepo;
import com.currency.exchange.currencyExchanger.repository.InfoResponseRepo;
import com.currency.exchange.currencyExchanger.repository.TestEntityJsonRepo;
import com.currency.exchange.currencyExchanger.service.CurrentService;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController()
@RequestMapping("api/v1/currency")
public class MainController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private TestEntityJsonRepo testEntityJsonRepo;
    @Autowired
            private FreeCurrencyApiRepo freeCurrencyApiRepo;
    @Autowired
            private InfoResponseRepo infoResponseRepo;
    @Autowired
            private MyDTOJsonConvertor myDTOJsonConvertor;



    CurrentService currentService;

    public MainController(RestTemplateBuilder restTemplateBuilder, CurrentService currentService) {
        this.restTemplate = restTemplateBuilder.build();
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

    @GetMapping(path = "/tests",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonBlock> testMethod() {


        ResponseEntity<JsonBlock> forEntity = restTemplate.getForEntity("https://freecurrencyapi.net/api/v2/historical?apikey=dbf681d0-5a99-11ec-b1d9-c3c47dc862f8" +
                        "&base_currency=USD&date_from=2021-12-14&date_to=2021-12-16"
                , JsonBlock.class);


        TestEntityJson testEntityJson =
                new TestEntityJson(forEntity.getBody());

        testEntityJsonRepo.save(testEntityJson);

        Optional<TestEntityJson> byId = testEntityJsonRepo.findById(1l);

        //нашел определенный валюту в конкретным дне, и создал мапу дата - курс валюты
//        if(byId.isPresent()){
//            JsonBlock data = byId.get().getData();
//            Map<String, Map<String, Double>> data1 = data.getData();
//            Map<String, Double> result = new HashMap<>();

//            data1.entrySet()
//                    .stream()
//                    .forEach(a -> {
//
//                        String key = a.getKey();
//
//                        Optional<Map.Entry<String, Double>> mop = Optional.ofNullable(a.getValue().entrySet()
//                                .stream()
//                                .filter(b -> b.getKey().equalsIgnoreCase("Mop"))
//                                .findFirst().orElseThrow(() -> {
//                                    throw new CurrenValueNotFoundException("date" + key + " not found value current");
//                                }));
//                        mop.ifPresent(stringDoubleEntry -> result.put(key, stringDoubleEntry.getValue()));
//
//                    });
//
//            System.out.println(result.size());
//            result.forEach((key, value) -> System.out.printf("%s : %f\n", key,value));
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }


        return byId.map(entityJson -> new ResponseEntity<>(entityJson.getData(),
                HttpStatus.OK)).orElse(null);

    }
}


