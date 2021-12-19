package com.currency.exchange.currencyExchanger.entity;

import javax.persistence.*;
import java.util.Map;

@Entity
public class CodeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    Map<String, Double> codeValue;

    public CodeValue() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Double> getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(Map<String, Double> codeValue) {
        this.codeValue = codeValue;
    }
}
