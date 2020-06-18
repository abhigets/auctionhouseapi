package com.api.auctionhouseapi;

import com.api.auctionhouseapi.domain.Sell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SellMapTest {

    @Test
    public void verifyItemExistInSaleMap() {
        Sell sell = new Sell();
        sell.build("10|1|SELL|toaster_1|10.00|20");
        AuctionMap.addToMap(sell);
        assertEquals(true, AuctionMap.isAuctionPresent("toaster_1"));
    }

    @Test
    public void addSaleToMapAndGetItemFromMap() {
        Sell sell = new Sell();
        sell.build("10|1|SELL|toaster_1|10.00|20");
        AuctionMap.addToMap(sell);
        assertEquals("10|1|SELL|toaster_1|10.00|20", AuctionMap.getAuction("toaster_1").toString());
    }

    @Test
    public void verifyTheMapReturnsAppropriateCount() {
        Sell sell = new Sell();
        sell.build("10|1|SELL|toaster_1|10.00|20");
        AuctionMap.addToMap(sell);
        sell.build("15|8|SELL|tv_1|250.00|20");
        AuctionMap.addToMap(sell);
        assertEquals(2, AuctionMap.getCount());
    }

    @Test
    public void verifyAllTheAuctionFromMapCanBeRemove() {
        Sell sell = new Sell();
        sell.build("10|1|SELL|toaster_1|10.00|20");
        AuctionMap.addToMap(sell);
        sell.build("15|8|SELL|tv_1|250.00|20");
        AuctionMap.addToMap(sell);
        AuctionMap.removeAllAuction();
        assertEquals(0,AuctionMap.getCount());
    }
}