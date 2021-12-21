package com.currency.exchange.currencyExchanger.exceptionHendler;

public class JsonNotFoundException extends RuntimeException{
    public JsonNotFoundException(String message) {
        super("couldn't find val by value " + message);
    }
}
