package com.currency.exchange.currencyExchanger.entity;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.util.Date;


@Embeddable
public class ResponseQuery {
    private String apikey;
    private Timestamp timestamp;
    private String base_currency;

    public ResponseQuery() {
    }

    public ResponseQuery(String apikey, Timestamp timestamp, String base_currency) {
        this.apikey = apikey;
        this.timestamp = timestamp;
        this.base_currency = base_currency;
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
}
