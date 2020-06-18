package com.api.auctionhouseapi.domain;


import static com.api.auctionhouseapi.util.MyFormatter.formatDecimalTo2Decimal;

public class Sell {
    private int timeStamp;
    private int userId;
    private String action;
    private String item;
    private double reservePrice;
    private int closeTime;

    public Sell(int timeStamp, int userId, String action, String item, double reservePrice, int closeTime) {
        this.timeStamp = timeStamp;
        this.userId = userId;
        this.action = action;
        this.item = item;
        this.reservePrice = reservePrice;
        this.closeTime = closeTime;
    }

    public Sell() {
    }

    public static boolean isAuctionString(String currentItem) {
        if (currentItem.contains("SELL")) {
            try {
                new Sell().build(currentItem);
            }catch (Exception ex) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isAuctionString(Object currentRecord) {
        try {
            Sell sell = (Sell) currentRecord;
            if (sell.action.equals("SELL"))
                return true;
        } catch (ClassCastException ex) {
        }
        return false;
    }

    public Sell build(String currentRecord) {
        String[] currentRecordObj = currentRecord.split("\\|");
        timeStamp = Integer.parseInt(currentRecordObj[0]);
        userId = Integer.parseInt(currentRecordObj[1]);
        action = currentRecordObj[2];
        item = currentRecordObj[3];
        reservePrice = Double.parseDouble(currentRecordObj[4]);
        closeTime = Integer.parseInt(currentRecordObj[5]);
        return this;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public String getItem() {
        return item;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public String toString() {
        return timeStamp + "|"
                + userId + "|"
                + action + "|"
                + item + "|"
                + formatDecimalTo2Decimal(reservePrice) + "|"
                + closeTime;
    }
}
