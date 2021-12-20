package com.currency.exchange.currencyExchanger.repository;

import com.currency.exchange.currencyExchanger.entity.freecurrencyapi.HistoryJsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryJsonEntityRepo extends JpaRepository<HistoryJsonEntity, Long> {
}
