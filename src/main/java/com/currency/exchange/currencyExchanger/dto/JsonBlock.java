package com.currency.exchange.currencyExchanger.dto;

import com.currency.exchange.currencyExchanger.entity.ResponseQuery;

import java.util.Map;

public class JsonBlock {

    private QueryResponse query;
    private Map<String, Map<String, Double>> data;



    public JsonBlock(QueryResponse query, Map<String, Map<String, Double>> data) {
        this.query = query;
        this.data = data;
    }

    public Map<String, Map<String, Double>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Double>> data) {
        this.data = data;
    }

    public JsonBlock() {
    }


    public QueryResponse getQuery() {
        return query;
    }

    public void setQuery(QueryResponse query) {
        this.query = query;
    }

}
