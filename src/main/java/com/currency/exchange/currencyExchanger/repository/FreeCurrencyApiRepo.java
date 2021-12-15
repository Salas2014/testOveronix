package com.currency.exchange.currencyExchanger.repository;

import com.currency.exchange.currencyExchanger.entity.RequestFreeCurrencyApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeCurrencyApiRepo extends JpaRepository<RequestFreeCurrencyApi, Long> {
}
