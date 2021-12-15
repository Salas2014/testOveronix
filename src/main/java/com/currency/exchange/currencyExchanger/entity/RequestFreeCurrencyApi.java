package com.currency.exchange.currencyExchanger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "request")
public class RequestFreeCurrencyApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private InfoResponse infoResponse;

    public RequestFreeCurrencyApi() {
    }

    public RequestFreeCurrencyApi(InfoResponse infoResponse) {
        this.infoResponse = infoResponse;
    }

    public RequestFreeCurrencyApi(String tag, InfoResponse infoResponse) {
        this.tag = tag;
        this.infoResponse = infoResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoResponse getInfoResponse() {
        return infoResponse;
    }

    public void setInfoResponse(InfoResponse infoResponse) {
        this.infoResponse = infoResponse;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
