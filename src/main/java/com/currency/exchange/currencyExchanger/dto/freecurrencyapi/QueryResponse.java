package com.currency.exchange.currencyExchanger.dto.freecurrencyapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;

public class QueryResponse {

    private String apikey;
    private Timestamp timestamp;
    private String base_currency;
    private String date_from;
    private String date_to;

    public QueryResponse(String apikey, Timestamp timestamp, String base_currency) {
        this.apikey = apikey;
        this.timestamp = timestamp;
        this.base_currency = base_currency;
    }

    public QueryResponse(String apikey, Timestamp timestamp, String base_currency, String date_from, String date_to) {
        this.apikey = apikey;
        this.timestamp = timestamp;
        this.base_currency = base_currency;
        this.date_from = date_from;
        this.date_to = date_to;
    }

    public QueryResponse() {
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(String base_currency) {
        this.base_currency = base_currency;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }
}
