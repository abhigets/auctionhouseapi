package com.api.auctionhouseapi.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyFormatterTest {

    @Test
    public void verifyDecimalNumberIsFormatedTo2Decimal() {
        assertEquals("10.00",MyFormatter.formatDecimalTo2Decimal(10.0));
        assertEquals("10.12",MyFormatter.formatDecimalTo2Decimal(10.12345));
    }
}