package com.api.auctionhouseapi.domain;

import com.api.auctionhouseapi.util.MyFormatter;

public class Bid {
    private int timeStamp;
    private int userId;
    private String action;
    private String item;
    private double bidAmount;

    public Bid(int timeStamp, int userId, String action, String item, double bidAmount) {
        this.timeStamp = timeStamp;
        this.userId = userId;
        this.action = action;
        this.item = item;
        this.bidAmount = bidAmount;
    }

    public Bid build(String currentItem) {
        String[] currentRecordObj = currentItem.split("\\|");
        timeStamp = Integer.parseInt(currentRecordObj[0]);
        userId = Integer.parseInt(currentRecordObj[1]);
        action = currentRecordObj[2];
        item = currentRecordObj[3];
        bidAmount = Double.parseDouble(currentRecordObj[4]);
        return this;
    }

    public Bid() {
    }

    public static boolean isStringBid(String bidString) {
        if (bidString.contains("BID")) {
            try {
                new Bid().build(bidString);
            }catch (Exception ex){
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isStringBid(Object currentRecord) {
        try {
            Bid bid = (Bid) currentRecord;
            if (bid.action.equals("BID"))
                return true;
        } catch (ClassCastException ex) {
        }
        return false;
    }

    public boolean isBidValid(int auctionStartTime, int auctionCloseTime, double highestBidAmount, double reservePrice) {
        if (timeStamp > auctionStartTime && timeStamp < auctionCloseTime && bidAmount > highestBidAmount && bidAmount > reservePrice)
            return true;
        return false;
    }

    public int getUserId() {
        return userId;
    }

    public String getItem() {
        return item;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public String toString() {
        return timeStamp + "|"
                + userId + "|"
                + action + "|"
                + item + "|"
                + MyFormatter.formatDecimalTo2Decimal(bidAmount);
    }
}
