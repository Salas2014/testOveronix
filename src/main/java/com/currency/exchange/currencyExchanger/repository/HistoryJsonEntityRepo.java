package com.currency.exchange.currencyExchanger.repository;

import com.currency.exchange.currencyExchanger.entity.freecurrencyapi.HistoryJsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryJsonEntityRepo extends JpaRepository<HistoryJsonEntity, Long> {


}
