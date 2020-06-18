package com.api.auctionhouseapi.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SellTest {

    @Test
    public void verifyIfTheInputAuctionIsOfTypeAuction() {
        assertEquals(true, Sell.isAuctionString("10|1|SELL|toaster_1|10.00|20"));
    }

    @Test
    public void verifyInvalidInputStringOfTypeAuction() {
        assertEquals(false, Sell.isAuctionString("10|1|SELL|toaster_1||20"));
    }

    @Test
    public void verifyInvalidInputStringOfDifferentType() {
        assertEquals(false, Sell.isAuctionString("12|8|BID|toaster_1|7.50"));
    }


    @Test
    public void verifyIfTheObjectIsOfAuction() {
        Sell sell = new Sell();
        sell.build("10|1|SELL|toaster_1|10.00|20");
        assertEquals(true, Sell.isAuctionString(sell));
    }

    @Test
    public void verifyIssdfTheObjectIsNotOfTypAuction() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");
        assertEquals(false, Sell.isAuctionString(bid));
    }

    @Test
    public void verifyGetterAndSetterOfAuction() {
        Sell sell = new Sell();
        sell.build("10|1|SELL|toaster_1|10.00|20");
        assertEquals(10, sell.getTimeStamp());
        assertEquals("toaster_1", sell.getItem());
        assertEquals(10.0, sell.getReservePrice(), 0);
        assertEquals(20, sell.getCloseTime());
        assertEquals("10|1|SELL|toaster_1|10.00|20", sell.toString());
    }
}
