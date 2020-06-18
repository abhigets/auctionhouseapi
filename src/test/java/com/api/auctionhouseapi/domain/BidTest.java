package com.api.auctionhouseapi.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BidTest {

    @Test
    public void verifyIfTheInputStringIsOfTypeBid() {
        assertEquals(true, Bid.isStringBid("12|8|BID|toaster_1|7.50"));
    }

    @Test
    public void verifyInvalidInputStringOfTypeBid() {
        assertEquals(false, Bid.isStringBid("12||BID|toaster_1|7.50"));
    }

    @Test
    public void verifyIfTheInputStringIsNotOfTypeBid() {
        assertEquals(false, Bid.isStringBid("12|8|SELL|toaster_1|7.50"));
    }

    @Test
    public void verifyIfTheObjectIsOfTypeBid() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");
        assertEquals(true, Bid.isStringBid(bid));
    }

    @Test
    public void verifyIfTheObjectIsNotOfTypeBid() {
        Sell sell = new Sell();
        sell.build("10|1|SELL|toaster_1|10.00|20");
        assertEquals(false, Bid.isStringBid(sell));
    }

    @Test
    public void verifyBidIsRejectedIsItsLessThenReservePrice() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");

        // bid comes when auction is valid but is less then reserved price
        int auctionStartTime = 10;
        int auctionCloseTime = 20;
        int highestBidAmount = 0;
        int reservePrice = 12;
        assertEquals(false, bid.isBidValid(auctionStartTime, auctionCloseTime, highestBidAmount, reservePrice));
    }

    @Test
    public void verifyBidIsRejectedIfItAppearBeforeAuctionStarts() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");

        // bid comes when auction is valid but is less then reserved price
        int auctionStartTime = 13;
        int auctionCloseTime = 20;
        int highestBidAmount = 0;
        int reservePrice = 5;
        assertEquals(false, bid.isBidValid(auctionStartTime, auctionCloseTime, highestBidAmount, reservePrice));
    }

    @Test
    public void verifyBidIsRejectedIfItAppearsAfterAuctionStops() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");

        // bid comes when auction is valid but is less then reserved price
        int auctionStartTime = 10;
        int auctionCloseTime = 11;
        int highestBidAmount = 0;
        int reservePrice = 5;
        assertEquals(false, bid.isBidValid(auctionStartTime, auctionCloseTime, highestBidAmount, reservePrice));
    }

    @Test
    public void verifyBidIsRejectedIfItsLessThenPreviousBidsAmounts() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");

        // bid comes when auction is valid but is less then reserved price
        int auctionStartTime = 10;
        int auctionCloseTime = 20;
        int highestBidAmount = 10;
        int reservePrice = 5;
        assertEquals(false, bid.isBidValid(auctionStartTime, auctionCloseTime, highestBidAmount, reservePrice));
    }

    @Test
    public void verifyBidIsAcceptsIfAppearsWhenAucationIsActiveAndWhenItsHighestBidAndIsGreaterThenReservedPrice() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");

        // bid comes when auction is valid but is less then reserved price
        int auctionStartTime = 10;
        int auctionCloseTime = 20;
        int highestBidAmount = 6;
        int reservePrice = 5;
        assertEquals(true, bid.isBidValid(auctionStartTime, auctionCloseTime, highestBidAmount, reservePrice));
    }

    @Test
    public void verifyGetterAndSetterValues() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");

        assertEquals(8, bid.getUserId());
        assertEquals("toaster_1", bid.getItem());
        assertEquals(7.50, bid.getBidAmount(), 0);
        assertEquals("12|8|BID|toaster_1|7.50", bid.toString());
    }
}