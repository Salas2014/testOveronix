package com.currency.exchange.currencyExchanger.repository;

import com.currency.exchange.currencyExchanger.entity.freecurrencyapi.JsonBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonBlockRepo extends JpaRepository<JsonBlockEntity, Long> {
}
