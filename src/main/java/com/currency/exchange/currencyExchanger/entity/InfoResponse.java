package com.currency.exchange.currencyExchanger.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
public class InfoResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ResponseQuery query;

    @ElementCollection
    private Map<String, Double> data;

    public InfoResponse( ResponseQuery query, Map<String, Double> data) {

        this.query = query;
        this.data = data;
    }

    public InfoResponse() {
    }



    public ResponseQuery getQuery() {
        return query;
    }

    public void setQuery(ResponseQuery query) {
        this.query = query;
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void setData(Map<String, Double> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "id="  +

                ", query=" + query +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoResponse that = (InfoResponse) o;
        return Objects.equals(query, that.query) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, data);
    }
}
