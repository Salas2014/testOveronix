package com.currency.exchange.currencyExchanger.entity;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.util.Date;


@Embeddable
public class ResponseQuery {
    private String apikey;
    private Timestamp timestamp;
    private String base_currency;
    private Date date_from;
    private Date date_to;

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public ResponseQuery() {
    }

    public ResponseQuery(String apikey, Timestamp timestamp, String base_currency) {
        this.apikey = apikey;
        this.timestamp = timestamp;
        this.base_currency = base_currency;
    }

    public ResponseQuery(String apikey, Timestamp timestamp, String base_currency, Date date_from, Date date_to) {
        this.apikey = apikey;
        this.timestamp = timestamp;
        this.base_currency = base_currency;
        this.date_from = date_from;
        this.date_to = date_to;
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
