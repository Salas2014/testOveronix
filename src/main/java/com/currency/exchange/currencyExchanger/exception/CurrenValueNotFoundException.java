package com.currency.exchange.currencyExchanger.exception;

public class CurrenValueNotFoundException extends RuntimeException{
    public CurrenValueNotFoundException(String message) {
        super("Current " + message + " doesn't exist");
    }
}
