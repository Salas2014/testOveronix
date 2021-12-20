package com.currency.exchange.currencyExchanger.dto.freecurrencyapi;

import java.util.Map;
import java.util.Objects;

public class JsonBlock {
    private QueryResponse query;
    private Map<String, Double> data;

    public JsonBlock(QueryResponse query, Map<String, Double> data) {
        this.query = query;
        this.data = data;
    }

    public JsonBlock() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonBlock jsonBlock = (JsonBlock) o;
        return Objects.equals(query, jsonBlock.query) && Objects.equals(data, jsonBlock.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, data);
    }

    public QueryResponse getQuery() {
        return query;
    }

    public void setQuery(QueryResponse query) {
        this.query = query;
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void setData(Map<String, Double> data) {
        this.data = data;
    }
}
