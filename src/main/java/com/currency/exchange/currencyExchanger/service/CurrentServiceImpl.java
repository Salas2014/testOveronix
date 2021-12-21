package com.currency.exchange.currencyExchanger.service;

import com.currency.exchange.currencyExchanger.dto.exchangerateapi.JsonBlockExchangeRateApi;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.HistoryJsonBlock;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.JsonBlock;
import com.currency.exchange.currencyExchanger.entity.exchangerateapi.JsonBlockExchangeRateApiEntity;
import com.currency.exchange.currencyExchanger.entity.freecurrencyapi.HistoryJsonEntity;
import com.currency.exchange.currencyExchanger.entity.freecurrencyapi.JsonBlockEntity;
import com.currency.exchange.currencyExchanger.entity.request.RequestFreeCurrencyApi;
import com.currency.exchange.currencyExchanger.exceptionHendler.JsonBadRequestException;
import com.currency.exchange.currencyExchanger.exceptionHendler.JsonNotFoundException;
import com.currency.exchange.currencyExchanger.repository.HistoryJsonEntityRepo;
import com.currency.exchange.currencyExchanger.repository.JsonBlockRepo;
import com.currency.exchange.currencyExchanger.repository.RequestFreeCurrencyApiRepo;
import com.currency.exchange.currencyExchanger.util.DateValidator;
import com.currency.exchange.currencyExchanger.util.DateValidatorUsingDateFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

@Service
public class CurrentServiceImpl implements CurrentService {

    @Value("${value.url.free_currency_api}")
    private String baseUrl;
    @Value("${value.key.free_currency_api}")
    private String keyValue;
    @Value("${value.history.free_currency_api}")
    private String urlHistory;
    @Value("${value.url.exchangerate-api}")
    private String urlExchangeRateApi;
    @Value("${value.key.exchangerate-api}")
    private String keyExchangeRateApi;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(CurrentService.class.getName());
    @Autowired
    private RequestFreeCurrencyApiRepo requestFreeCurrencyApiRepo;
    @Autowired
    private JsonBlockRepo jsonBlockRepo;
    @Autowired
    private HistoryJsonEntityRepo historyJsonEntityRepo;
    private DateValidator validator = new DateValidatorUsingDateFormat("yyyy-MM-dd");

    public CurrentServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build(); }


    @Override
    public JsonBlock getValue(String base_currency) {
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

        ResponseEntity<String> serverResponse = restTemplate.getForEntity(urlBuild.toString(), String.class);
        if(serverResponse.getStatusCode().value() == 200) {
            try {
                JsonBlock jsonBlock = objectMapper.readValue(serverResponse.getBody(), JsonBlock.class);
                JsonBlockEntity jsonBlockEntity = new JsonBlockEntity(jsonBlock);
                RequestFreeCurrencyApi requestFreeCurrencyApi = new RequestFreeCurrencyApi();
                requestFreeCurrencyApi.setJsonBlockEntity(jsonBlockEntity);

                if(urlBuild.toString().contains("freecurrencyapi")){
                    requestFreeCurrencyApi.setTag("freecurrencyapi");
                }

                requestFreeCurrencyApiRepo.save(requestFreeCurrencyApi);
                return jsonBlock;
            } catch (JsonProcessingException exception) {
                throw new JsonBadRequestException("can not parse you params");
            }
        } else
            throw new JsonBadRequestException("format for you date yyyy-mm-dd and " +
                    "existing currency code format USD");
    }

    @Override
    public HistoryJsonBlock getHistoryExchange(String baseCurrency, String dateFrom, String dateTo){
        StringBuilder stringBuilder = new StringBuilder(urlHistory+keyValue);

        if(dateFrom != null && dateTo != null
                && !dateFrom.isEmpty() && !dateTo.isEmpty()
                && validator.isValid(dateFrom) && validator.isValid(dateTo)){

            if(baseCurrency == null || baseCurrency.isEmpty()){
                stringBuilder
                        .append("&base_currency=").append("USD")
                        .append("&date_from=").append(dateFrom)
                        .append("&date_to=").append(dateTo);
            }else {
                stringBuilder
                        .append("&base_currency=").append(baseCurrency)
                        .append("&date_from=").append(dateFrom)
                        .append("&date_to=").append(dateTo);
            }
        } else {
            throw new JsonBadRequestException("format for you date yyyy-mm-dd");
        }


        ResponseEntity<String> serverResponse = restTemplate.getForEntity(stringBuilder.toString(), String.class);
        if(serverResponse.getStatusCode().value() == 200){
            try {
                HistoryJsonBlock historyJsonBlock = objectMapper.readValue(serverResponse.getBody(), HistoryJsonBlock.class);
                HistoryJsonEntity historyJsonEntity = new HistoryJsonEntity(historyJsonBlock);
                RequestFreeCurrencyApi requestFreeCurrencyApi = new RequestFreeCurrencyApi();
                requestFreeCurrencyApi.setHistoryJsonEntity(historyJsonEntity);

                if(stringBuilder.toString().contains("freecurrencyapi")){
                    requestFreeCurrencyApi.setTag("freecurrencyapi");
                }

                requestFreeCurrencyApiRepo.save(requestFreeCurrencyApi);
                return historyJsonBlock;
            } catch (JsonProcessingException exception) {
                throw new JsonBadRequestException("can not parse you params");
            }
        } else
            throw new JsonBadRequestException("existing currency code format for instance: USD");
    }



    public Double selectBEstRate(String baseCode, String targetCode) {

        ResponseEntity<String> exchange;
        ResponseEntity<String> free;
        try {
            exchange = restTemplate.getForEntity(urlExchangeRateApi
                    + keyExchangeRateApi + baseCode, String.class);

            free = restTemplate.getForEntity(baseUrl + keyValue
                    + "&base_currency=" +baseCode, String.class);
        } catch (RestClientException e){
            throw new JsonNotFoundException(baseCode);
        }

        if(exchange.getStatusCode().value() == 200 && free.getStatusCode().value() == 200){
            try {

                JsonBlockExchangeRateApi jsonBlockExchangeRateApi = objectMapper.readValue(exchange.getBody(), JsonBlockExchangeRateApi.class);
                JsonBlock jsonBlock = objectMapper.readValue(free.getBody(), JsonBlock.class);
                RequestFreeCurrencyApi requestFreeCurrencyApi = new RequestFreeCurrencyApi();
                requestFreeCurrencyApi.setJsonBlockExchangeRateApi(new JsonBlockExchangeRateApiEntity(jsonBlockExchangeRateApi));
                requestFreeCurrencyApi.setJsonBlockEntity(new JsonBlockEntity(jsonBlock));
                requestFreeCurrencyApiRepo.save(requestFreeCurrencyApi);

                return Math.max(findValue(jsonBlockExchangeRateApi.getConversion_rates(), targetCode),
                        findValue(jsonBlock.getData(), targetCode));

            } catch (JsonProcessingException exception) {
                throw new JsonBadRequestException("can not parse you params");
            }
        }
        else {
            throw new JsonBadRequestException("existing currency code format for instance: USD");
        }

    }


    private Double findValue(Map<String, Double> data, String targetValue){
       return data.entrySet()
                .stream()
                .filter((o) -> o.getKey().equals(targetValue))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() ->  new JsonBadRequestException(targetValue + " has Exception"));
    }


    @Override
    public List<String> getCurrencyCode() {
        return new ArrayList<>(Objects.requireNonNull(restTemplate.getForEntity(baseUrl + keyValue, JsonBlock.class)
                .getBody()).getData().keySet());
    }
}
