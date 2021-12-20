package com.currency.exchange.currencyExchanger.convertor.exchangerateapi;

import com.currency.exchange.currencyExchanger.convertor.freecurrencyapi.HistoryJsonBlockConvertor;
import com.currency.exchange.currencyExchanger.dto.exchangerateapi.JsonBlockExchangeRateApi;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.HistoryJsonBlock;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.logging.Logger;

@Converter
@Component
public class JsonBlockConvertorExchangeRateApiConvertor implements AttributeConverter<JsonBlockExchangeRateApi, String> {
    Logger logger = Logger.getLogger(HistoryJsonBlockConvertor.class.getName());

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonBlockExchangeRateApi jsonBlockConvertorExchangeRateApi) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(jsonBlockConvertorExchangeRateApi);
        } catch (JsonProcessingException jpe) {
            // Handle exception
            logger.info("convert to data column exs");
        }
        return json;
    }

    @Override
    public JsonBlockExchangeRateApi convertToEntityAttribute(String s) {
        JsonBlockExchangeRateApi myPojo = null;
        try {
            myPojo = objectMapper.readValue(s, JsonBlockExchangeRateApi.class);
        } catch (JsonParseException e) {
            logger.info("convert to entity exs");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPojo;
    }
}
