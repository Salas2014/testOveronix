package com.currency.exchange.currencyExchanger.repository;

import com.currency.exchange.currencyExchanger.entity.request.RequestFreeCurrencyApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestFreeCurrencyApiRepo extends JpaRepository<RequestFreeCurrencyApi, Long> {
}
