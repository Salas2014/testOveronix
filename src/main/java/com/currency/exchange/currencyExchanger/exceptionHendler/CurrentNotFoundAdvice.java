package com.currency.exchange.currencyExchanger.exceptionHendler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CurrentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CurrenValueNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String currentValueNotFoundHandler(CurrenValueNotFoundException exception){
        return exception.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(JsonBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String jsonBadRequestException(JsonBadRequestException exception){
        return exception.getMessage();
    }
}
