package com.currency.exchange.currencyExchanger.entity.request;

import com.currency.exchange.currencyExchanger.dto.exchangerateapi.JsonBlockExchangeRateApi;
import com.currency.exchange.currencyExchanger.entity.exchangerateapi.JsonBlockExchangeRateApiEntity;
import com.currency.exchange.currencyExchanger.entity.freecurrencyapi.HistoryJsonEntity;
import com.currency.exchange.currencyExchanger.entity.freecurrencyapi.JsonBlockEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class RequestFreeCurrencyApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private HistoryJsonEntity historyJsonEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private JsonBlockEntity jsonBlockEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private JsonBlockExchangeRateApiEntity jsonBlockExchangeRateApi;

    public RequestFreeCurrencyApi() {
    }

    public RequestFreeCurrencyApi(String tag, HistoryJsonEntity infoResponse, JsonBlockEntity jsonBlockEntity) {
        this.tag = tag;
        this.historyJsonEntity = infoResponse;
        this.jsonBlockEntity = jsonBlockEntity;
    }

    public RequestFreeCurrencyApi(String tag, JsonBlockEntity jsonBlockEntity) {
        this.tag = tag;
        this.jsonBlockEntity = jsonBlockEntity;
    }

    public RequestFreeCurrencyApi(String tag, HistoryJsonEntity infoResponse) {
        this.tag = tag;
        this.historyJsonEntity = infoResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public HistoryJsonEntity getHistoryJsonEntity() {
        return historyJsonEntity;
    }

    public void setHistoryJsonEntity(HistoryJsonEntity historyJsonEntity) {
        this.historyJsonEntity = historyJsonEntity;
    }

    public JsonBlockEntity getJsonBlockEntity() {
        return jsonBlockEntity;
    }

    public void setJsonBlockEntity(JsonBlockEntity jsonBlockEntity) {
        this.jsonBlockEntity = jsonBlockEntity;
    }

    public JsonBlockExchangeRateApiEntity getJsonBlockExchangeRateApi() {
        return jsonBlockExchangeRateApi;
    }

    public void setJsonBlockExchangeRateApi(JsonBlockExchangeRateApiEntity jsonBlockExchangeRateApi) {
        this.jsonBlockExchangeRateApi = jsonBlockExchangeRateApi;
    }
}
