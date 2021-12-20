package com.currency.exchange.currencyExchanger.convertor.freecurrencyapi;

import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.JsonBlock;
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
public class JsonBlockConvertor implements AttributeConverter<JsonBlock, String> {
    Logger logger = Logger.getLogger(HistoryJsonBlockConvertor.class.getName());

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(JsonBlock jsonBlockEntity) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(jsonBlockEntity);
        } catch (JsonProcessingException jpe) {
            logger.info("convert to data column exs");
        }
        return json;
    }

    @Override
    public JsonBlock convertToEntityAttribute(String myPojoAsJson) {
        JsonBlock myPojo = null;
        try {
            myPojo = objectMapper.readValue(myPojoAsJson, JsonBlock.class);
        } catch (JsonParseException e) {
            logger.info("convert to entity exs");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPojo;
    }
}
