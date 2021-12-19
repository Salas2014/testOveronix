package com.currency.exchange.currencyExchanger.convertor;

import com.currency.exchange.currencyExchanger.dto.JsonBlock;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.logging.Logger;

@Converter
@Component
public class MyDTOJsonConvertor implements AttributeConverter<JsonBlock, String> {

    Logger logger = Logger.getLogger(MyDTOJsonConvertor.class.getName());

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonBlock myPojo) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(myPojo);
        } catch (JsonProcessingException jpe) {
            // Handle exception
        }
        return json;
    }

    @Override
    public JsonBlock convertToEntityAttribute(String myPojoAsJson) {
        JsonBlock myPojo = null;
        try {
            myPojo = objectMapper.readValue(myPojoAsJson, JsonBlock.class);
        } catch (JsonParseException e) {

            // HandleException

        } catch (IOException e) {
            // HandleException


        }
        return myPojo;
    }


}
