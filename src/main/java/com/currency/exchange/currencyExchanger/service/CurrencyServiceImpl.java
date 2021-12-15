package com.currency.exchange.currencyExchanger.service;

import com.currency.exchange.currencyExchanger.entity.InfoResponse;
import com.currency.exchange.currencyExchanger.entity.RequestFreeCurrencyApi;
import com.currency.exchange.currencyExchanger.exception.CurrenValueNotFoundException;
import com.currency.exchange.currencyExchanger.exception.JsonBadRequestException;
import com.currency.exchange.currencyExchanger.repository.FreeCurrencyApiRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceImpl implements CurrentService {
    private final RestTemplate restTemplate;
    @Value("${value.url.free_currency_api}")
    private String baseUrl;
    @Value("${value.key.free_currency_api}")
    private String keyValue;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private final FreeCurrencyApiRepo freeCurrencyApiRepo;


    public CurrencyServiceImpl(RestTemplateBuilder restTemplateBuilder,
                               FreeCurrencyApiRepo freeCurrencyApiRepo
                              ) {
        this.restTemplate = restTemplateBuilder.build();
        this.freeCurrencyApiRepo = freeCurrencyApiRepo;

    }

    @Override
    public InfoResponse getValue(String base_currency) {
        StringBuilder urlBuild = new StringBuilder();
        if (base_currency != null) {
            // check base_currency and build request
            urlBuild.append(baseUrl)
                    .append("base_currency=").append(base_currency)
                    .append("&").append(keyValue);
        } else {
            // Default currency = USD
            urlBuild.append(baseUrl).append(keyValue);
        }


        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);

        if (forEntity.getStatusCode().value() == 200) {
            String body = forEntity.getBody();
            try {
                InfoResponse infoResponse = objectMapper.readValue(body, InfoResponse.class);
                RequestFreeCurrencyApi requestFreeCurrencyApi = new RequestFreeCurrencyApi(infoResponse);
                if(urlBuild.toString().contains("freecurrencyapi")){
                    requestFreeCurrencyApi.setTag("freecurrencyapi");
                }
                freeCurrencyApiRepo.save(requestFreeCurrencyApi);
                return infoResponse;
            } catch (JsonProcessingException e) {
               throw new JsonBadRequestException(base_currency);
            }
        } else {
            throw new CurrenValueNotFoundException(base_currency);
        }
    }
}
