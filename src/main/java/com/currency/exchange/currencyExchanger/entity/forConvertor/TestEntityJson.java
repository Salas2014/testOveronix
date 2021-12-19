package com.currency.exchange.currencyExchanger.entity.forConvertor;

import com.currency.exchange.currencyExchanger.convertor.MyDTOJsonConvertor;
import com.currency.exchange.currencyExchanger.dto.JsonBlock;
import com.currency.exchange.currencyExchanger.dto.QueryResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TestEntityJson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Convert(converter = MyDTOJsonConvertor.class)
    @Lob
    private JsonBlock data;


    public TestEntityJson() {
    }

    public TestEntityJson(JsonBlock data) {
        this.data = data;
    }

    public JsonBlock getData() {
        return data;
    }

    public void setData(JsonBlock data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestEntityJson{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntityJson that = (TestEntityJson) o;
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


}
