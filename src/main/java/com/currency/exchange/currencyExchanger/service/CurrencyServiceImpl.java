package com.currency.exchange.currencyExchanger.service;

import com.currency.exchange.currencyExchanger.entity.InfoResponse;
import com.currency.exchange.currencyExchanger.entity.request.RequestFreeCurrencyApi;
import com.currency.exchange.currencyExchanger.exceptionHendler.CurrenValueNotFoundException;
import com.currency.exchange.currencyExchanger.exceptionHendler.JsonBadRequestException;
import com.currency.exchange.currencyExchanger.repository.FreeCurrencyApiRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CurrencyServiceImpl implements CurrentService {
    private final RestTemplate restTemplate;
    @Value("${value.url.free_currency_api}")
    private String baseUrl;
    @Value("${value.key.free_currency_api}")
    private String keyValue;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(CurrentService.class.getName());

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
            logger.info(forEntity.getBody());
            try {
                InfoResponse infoResponse = objectMapper.readValue(body, InfoResponse.class);
                RequestFreeCurrencyApi requestFreeCurrencyApi = new RequestFreeCurrencyApi(infoResponse);
                if(urlBuild.toString().contains("freecurrencyapi")){
                    requestFreeCurrencyApi.setTag("freecurrencyapi");
                }
                freeCurrencyApiRepo.save(requestFreeCurrencyApi);
                return infoResponse;
            } catch (JsonProcessingException e) {
                logger.info("can't parse json");
               throw new JsonBadRequestException(base_currency);
            } catch (IOException e) {
                //зарешать этот момент
                throw new JsonBadRequestException(base_currency);
            }
        } else {
            throw new CurrenValueNotFoundException(base_currency);
        }
    }

    @Override
    public List getCurrencyCode(String tag) {
        InfoResponse value = this.getValue("");
        return null;

//                value.getData().entrySet()
//                .stream()
//                .map((Function<Map.Entry<String, Double>, Object>) Map.Entry::getKey)
//                .collect(Collectors.toList());
    }
}
