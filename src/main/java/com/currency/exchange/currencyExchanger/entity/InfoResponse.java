package com.currency.exchange.currencyExchanger.entity;

import javax.persistence.*;

@Entity
public class InfoResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ResponseQuery query;


    @OneToOne(cascade = CascadeType.ALL)
    private DateHistory data;

    public InfoResponse(DateHistory data) {
        this.data = data;
    }

    public DateHistory getData() {
        return data;
    }

    public void setData(DateHistory data) {
        this.data = data;
    }

    public InfoResponse(Long id) {
        this.id = id;
    }

    public InfoResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseQuery getQuery() {
        return query;
    }

    public void setQuery(ResponseQuery query) {
        this.query = query;
    }



    @Override
    public String toString() {
        return "InfoResponse{" +
                "id=" + id +
                ", query=" + query +
                ", data=" + data +
                '}';
    }
}
