package com.api.auctionhouseapi.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuctionResultTest {

    @Test
    public void verifyAllGetterAndSetterForAuctionResult() {
        AuctionResult auctionResult = new AuctionResult(0,
                "toaster_1",
                0,
                "UNSOLD",
                0.00,
                0,
                0.0,
                0.0
        );
        auctionResult.setUserId(5);
        auctionResult.setTimeStamp(10);
        auctionResult.setHighestBid(7.5);
        auctionResult.incrementTotalBidCount();
        assertEquals("10|toaster_1|5|UNSOLD|0.00|1|7.50|0.00",auctionResult.toString());
        assertEquals(7.5,auctionResult.getHighestBid(),0);
        assertEquals("toaster_1",auctionResult.getItem());
    }

    @Test
    public void verifyAuctionResultsCanIncrementBidCount() {
        AuctionResult auctionResult = new AuctionResult(0,
                "toaster_1",
                0,
                "UNSOLD",
                0.00,
                0,
                0.0,
                0.0
        );
        auctionResult.incrementTotalBidCount();
        assertEquals("0|toaster_1||UNSOLD|0.00|1|0.00|0.00",auctionResult.toString());
    }

    @Test
    public void verifyLowestBidAmountIsUpdatedForOnlyFirstBid() {
        AuctionResult auctionResult = new AuctionResult(0,
                "toaster_1",
                0,
                "UNSOLD",
                0.00,
                0,
                0.0,
                0.0
        );
        auctionResult.updateLowestBidAmountIfItsFirstBid(12.5);
        assertEquals("0|toaster_1||UNSOLD|0.00|0|0.00|12.50",auctionResult.toString());
        auctionResult.incrementTotalBidCount();
        auctionResult.updateLowestBidAmountIfItsFirstBid(13.5);
        assertEquals("0|toaster_1||UNSOLD|0.00|1|0.00|12.50",auctionResult.toString());
    }

    @Test
    public void verifyTheStatusCanBeUpdatedToSOLD() {
        AuctionResult auctionResult = new AuctionResult(0,
                "toaster_1",
                0,
                "UNSOLD",
                0.00,
                0,
                0.0,
                0.0
        );
        auctionResult.updateStatusToSold();
        assertEquals("0|toaster_1||SOLD|0.00|0|0.00|0.00",auctionResult.toString());
    }

    @Test
    public void verifyReservedPriceIsSetForFirstValidBid() {
        AuctionResult auctionResult = new AuctionResult(0,
                "toaster_1",
                0,
                "UNSOLD",
                0.00,
                0,
                5.0,
                0.0
        );
        auctionResult.updatePricePaid(7.0);
        assertEquals("0|toaster_1||UNSOLD|7.00|0|5.00|0.00",auctionResult.toString());
    }

    @Test
    public void verifySecondHighestPriceIsSetAfterFirstValidBid() {
        AuctionResult auctionResult = new AuctionResult(0,
                "toaster_1",
                0,
                "UNSOLD",
                0.00,
                0,
                8.0,
                0.0
        );
        auctionResult.updatePricePaid(7.0);
        assertEquals("0|toaster_1||UNSOLD|8.00|0|8.00|0.00",auctionResult.toString());
    }
}