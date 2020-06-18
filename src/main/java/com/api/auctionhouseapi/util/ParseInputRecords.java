package com.api.auctionhouseapi.util;

import com.api.auctionhouseapi.domain.Bid;
import com.api.auctionhouseapi.domain.Heartbeat;
import com.api.auctionhouseapi.domain.Sell;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParseInputRecords {

    public List<Object> getParseRecords(String inputRecords) {
        List<Object> objectList = new ArrayList<Object>();
        String[] items = inputRecords.split("\n");
        for (String currentItem : items) {
            currentItem = currentItem.trim();
            if (Sell.isAuctionString(currentItem)) {
                objectList.add(new Sell().build(currentItem));
            }
            if (Bid.isStringBid(currentItem)) {
                objectList.add(new Bid().build(currentItem));
            }
            if (Heartbeat.isHearbeatString(currentItem)) {
                objectList.add(new Heartbeat().build(currentItem));
            }
        }
        return objectList;
    }
}
