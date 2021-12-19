package com.currency.exchange.currencyExchanger.repository;

import com.currency.exchange.currencyExchanger.entity.forConvertor.TestEntityJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestEntityJsonRepo extends JpaRepository<TestEntityJson, Long> {

}
