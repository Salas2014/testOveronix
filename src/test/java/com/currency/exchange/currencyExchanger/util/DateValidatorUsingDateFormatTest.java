package com.currency.exchange.currencyExchanger.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DateValidatorUsingDateFormatTest {

    @Test
    void isValid() {
        DateValidator validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");
        assertTrue(validator.isValid("02/28/2019"));
        assertFalse(validator.isValid("02/30/2019"));
    }
}