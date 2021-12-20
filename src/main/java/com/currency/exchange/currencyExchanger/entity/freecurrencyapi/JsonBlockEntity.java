package com.currency.exchange.currencyExchanger.entity.freecurrencyapi;

import com.currency.exchange.currencyExchanger.convertor.freecurrencyapi.JsonBlockConvertor;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.JsonBlock;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class JsonBlockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Convert(converter = JsonBlockConvertor.class)
    @Lob
    private JsonBlock data;

    public JsonBlockEntity() {
    }

    public JsonBlockEntity(JsonBlock data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonBlock getData() {
        return data;
    }

    public void setData(JsonBlock data) {
        this.data = data;
    }
}
