package com.currency.exchange.currencyExchanger.exception;

public class JsonBadRequestException extends RuntimeException{
    public JsonBadRequestException(String message) {
        super("wrong currency value: " + message);
    }
}
