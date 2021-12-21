package com.currency.exchange.currencyExchanger.service;

import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.HistoryJsonBlock;
import com.currency.exchange.currencyExchanger.dto.freecurrencyapi.JsonBlock;

import java.util.List;

public interface CurrentService {
    JsonBlock getValue(String base_currency);
    List<String> getCurrencyCode();
    HistoryJsonBlock getHistoryExchange(String dateFrom, String dateTo, String baseCurrency);

}
