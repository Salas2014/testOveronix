package com.currency.exchange.currencyExchanger.service;

import com.currency.exchange.currencyExchanger.exceptionHendler.JsonBadRequestException;
import com.currency.exchange.currencyExchanger.exceptionHendler.JsonNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrentServiceImplTest {
    ResponseEntity<String> response;
    @Autowired
    private MockMvc mockMvc;
    @Value("${value.url.free_currency_api}")
    private String baseUrl;
    @Value("${value.key.free_currency_api}")
    private String keyValue;

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = baseUrl + keyValue;

        response  = restTemplate.getForEntity(fooResourceUrl , String.class);
    }

    @Test
    public void getForEntity() throws Exception{

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void notNullBody() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("data");
        assertThat(name.asText(), notNullValue());
    }

    @Test
    public void ifCurrentValueWrong() throws Exception{
        this.mockMvc.perform(get("/api/v1/currency" + "?base_currency=Pop")
            ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof JsonBadRequestException))
                .andExpect(result -> assertEquals("wrong currency value: " +
                    "can not parse you params",
                    Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void selectBestRateBadRequest() throws Exception{

        this.mockMvc.perform(get("/api/v1/currency" +"/bestRate"
            +"?baseCode=SDR&targetCode="))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof JsonNotFoundException));

    }
    @Test
    public void badTargetCodeForSelectBaseRate() throws Exception{
        this.mockMvc.perform(get("/api/v1/currency" +"/bestRate"
                +"?baseCode=USD&targetCode=MPT"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof JsonBadRequestException))
                .andExpect(result -> assertEquals("wrong currency value: " +
                                "can not parse you params",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Disabled
    // my 3d api is not live andI can't know the code for sure
    public void selectBestRateHappyPass() throws Exception{
        this.mockMvc.perform(get("/api/v1/currency" +"/bestRate"
                +"?baseCode=USD&targetCode=EUR"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getHistoryExchangeNullableDate() throws Exception{
        this.mockMvc.perform(get("/api/v1/currency/history?"  ))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof JsonBadRequestException));
    }

    @Test
    public void getHistoryExchangeBadDate() throws Exception{
        this.mockMvc.perform(get("/api/v1/currency/history?" + "dateFrom=&" + "dateTo=" ))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof JsonBadRequestException));
    }


}