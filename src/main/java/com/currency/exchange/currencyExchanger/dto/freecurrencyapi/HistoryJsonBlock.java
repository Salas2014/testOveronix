package com.currency.exchange.currencyExchanger.dto.freecurrencyapi;

import java.util.Map;

public class HistoryJsonBlock {

    private QueryResponse query;
    private Map<String, Map<String, Double>> data;

    public HistoryJsonBlock() {
    }

    public HistoryJsonBlock(QueryResponse query, Map<String, Map<String, Double>> data) {
        this.query = query;
        this.data = data;
    }

    public Map<String, Map<String, Double>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Double>> data) {
        this.data = data;
    }

    public QueryResponse getQuery() {
        return query;
    }

    public void setQuery(QueryResponse query) {
        this.query = query;
    }

}
