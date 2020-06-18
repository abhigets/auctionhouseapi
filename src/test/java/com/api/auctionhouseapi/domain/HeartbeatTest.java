package com.api.auctionhouseapi.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeartbeatTest {

    @Test
    public void verifyIfTheInputStringIsOfTypeHearbeat() {
        assertEquals(true, Heartbeat.isHearbeatString("12"));
    }

    @Test
    public void verifyIfTheInputStringIsNotOfTypeHeartbeat() {
        assertEquals(false, Heartbeat.isHearbeatString("SELL"));
    }

    @Test
    public void verifyIfTheObjectIsOfTypeHeartbeat() {
        Heartbeat heartbeat = new Heartbeat();
        heartbeat.build("10");
        assertEquals(true, Heartbeat.isHeartbeat(heartbeat));
    }

    @Test
    public void verifyIfTheObjectIsNotOfTypeHeartbeat() {
        Bid bid = new Bid();
        bid.build("12|8|BID|toaster_1|7.50");
        assertEquals(false, Heartbeat.isHeartbeat(bid));
    }

    @Test
    public void verifyGetterAndSetterOfHeartbeat() {
        Heartbeat heartbeat = new Heartbeat(10);
        assertEquals(10,heartbeat.getTimeStamp());
    }
}