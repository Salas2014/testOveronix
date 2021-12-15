package com.currency.exchange.currencyExchanger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class CurrentService {
    ResponseEntity<String> response;
    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://freecurrencyapi.net/api/v2/latest?apikey=dbf681d0-5a99-11ec-b1d9-c3c47dc862f8";

        response  = restTemplate.getForEntity(fooResourceUrl , String.class);
    }

    @Test
    public void getForEntity(){

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void notNullBody() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("data");
        assertThat(name.asText(), notNullValue());
    }
}
