package com.currency.exchange.currencyExchanger.convertor.freecurrencyapi;

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
public class HistoryJsonBlockConvertor implements AttributeConverter<HistoryJsonBlock, String> {

    Logger logger = Logger.getLogger(HistoryJsonBlockConvertor.class.getName());

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(HistoryJsonBlock myPojo) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(myPojo);
        } catch (JsonProcessingException jpe) {
            // Handle exception
            logger.info("convert to data column exs");
        }
        return json;
    }

    @Override
    public HistoryJsonBlock convertToEntityAttribute(String myPojoAsJson) {
        HistoryJsonBlock myPojo = null;
        try {
            myPojo = objectMapper.readValue(myPojoAsJson, HistoryJsonBlock.class);
        } catch (JsonParseException e) {
            logger.info("convert to entity exs");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPojo;
    }


}
