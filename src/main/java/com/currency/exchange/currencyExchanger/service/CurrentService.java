package com.currency.exchange.currencyExchanger.service;

import com.currency.exchange.currencyExchanger.entity.InfoResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CurrentService {
    InfoResponse getValue(String base_currency);
    List getCurrencyCode(String tag);
}
