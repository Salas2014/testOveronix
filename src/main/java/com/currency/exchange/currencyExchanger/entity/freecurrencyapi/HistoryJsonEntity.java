package com.currency.exchange.currencyExchanger.entity.freecurrencyapi;

import com.currency.exchange.currencyExchanger.convertor.freecurrencyapi.HistoryJsonBlockConvertor;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.HistoryJsonBlock;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HistoryJsonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Convert(converter = HistoryJsonBlockConvertor.class)
    @Lob
    private HistoryJsonBlock data;

    public HistoryJsonEntity() {
    }

    public HistoryJsonEntity(HistoryJsonBlock data) {
        this.data = data;
    }


    public HistoryJsonBlock getData() {
        return data;
    }

    public void setData(HistoryJsonBlock data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryJsonEntity that = (HistoryJsonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HistoryJsonEntity{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}
