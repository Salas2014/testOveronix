package com.currency.exchange.currencyExchanger.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class DateHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "info_response_id")
    @JsonIgnore
    private InfoResponse infoResponse;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CodeValue> ob;

    public DateHistory(List<CodeValue> ob) {
        this.ob = ob;
    }

    public DateHistory() {

    }

    public List<CodeValue> getOb() {
        return ob;
    }

    public void setOb(List<CodeValue> ob) {
        this.ob = ob;
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

}
