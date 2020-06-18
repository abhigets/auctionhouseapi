package com.api.auctionhouseapi;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuctionHouse {

    public String process(List<Object> parseRecords) {
        ProcessAuction processAuction = new ProcessAuction();
        for (Object currentRecord : parseRecords) {
            processAuction.processIfSale(currentRecord);
            processAuction.processIfBid(currentRecord);
            processAuction.processIfHeartbeat(currentRecord);
        }
        return processAuction.toString();
    }
}
