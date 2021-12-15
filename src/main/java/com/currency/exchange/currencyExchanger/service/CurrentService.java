package com.currency.exchange.currencyExchanger.service;

import com.currency.exchange.currencyExchanger.entity.InfoResponse;

public interface CurrentService {
    InfoResponse getValue(String base_currency);
}
