package com.api.auctionhouseapi.domain;

import com.api.auctionhouseapi.util.MyFormatter;

public class AuctionResult {
    private int timeStamp;
    private String item;
    private int userId;
    private String status;
    private double pricePaid;
    private int totalBidCount;
    private double highestBid;
    private double lowestBid;

    private String formatUserId() {
        if (userId == 0)
            return "";
        return userId + "";
    }

    public AuctionResult(int timeStamp, String item, int userId, String status, double pricePaid, int totalBidCount, double highestBid, double lowestBid) {
        this.timeStamp = timeStamp;
        this.item = item;
        this.userId = userId;
        this.status = status;
        this.pricePaid = pricePaid;
        this.totalBidCount = totalBidCount;
        this.highestBid = highestBid;
        this.lowestBid = lowestBid;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getItem() {
        return item;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public void incrementTotalBidCount() {
        totalBidCount++;
    }

    public String toString() {
        return timeStamp + "|"
                + item + "|"
                + formatUserId() + "|"
                + status + "|"
                + MyFormatter.formatDecimalTo2Decimal(pricePaid) + "|"
                + totalBidCount + "|"
                + MyFormatter.formatDecimalTo2Decimal(highestBid) + "|"
                + MyFormatter.formatDecimalTo2Decimal(lowestBid);
    }

    public void updateLowestBidAmountIfItsFirstBid(double amount) {
        if (totalBidCount == 0)
            lowestBid = amount;
    }

    public void updateStatusToSold() {
        status = "SOLD";
    }

    public void updatePricePaid(double reservePrice) {
        double price = highestBid;
        if (price < reservePrice)
            price = reservePrice;
        pricePaid = price;
    }
}
