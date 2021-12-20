package com.currency.exchange.currencyExchanger.entity.exchangerateapi;


import com.currency.exchange.currencyExchanger.convertor.exchangerateapi.JsonBlockConvertorExchangeRateApiConvertor;
import com.currency.exchange.currencyExchanger.convertor.freecurrencyapi.JsonBlockConvertor;
import com.currency.exchange.currencyExchanger.dto.exchangerateapi.JsonBlockExchangeRateApi;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.JsonBlock;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class JsonBlockExchangeRateApiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Convert(converter = JsonBlockConvertorExchangeRateApiConvertor.class)
    @Lob
    private JsonBlockExchangeRateApi conversion_rates;

    public JsonBlockExchangeRateApiEntity(JsonBlockExchangeRateApi conversion_rates) {
        this.conversion_rates = conversion_rates;
    }

    public JsonBlockExchangeRateApiEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonBlockExchangeRateApi getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(JsonBlockExchangeRateApi conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
