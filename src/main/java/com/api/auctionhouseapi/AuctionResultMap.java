package com.api.auctionhouseapi;

import com.api.auctionhouseapi.domain.AuctionResult;

import java.util.HashMap;
import java.util.Map;

public class AuctionResultMap {
    private static Map<String, AuctionResult> auctionResultMap = new HashMap<String, AuctionResult>();

    public static AuctionResult getAuctionFrom(String auctionName) {
        return auctionResultMap.get(auctionName);
    }

    public static void updateLowestBidAmountIfItsFirstBid(String auctionName, double bidAmount) {
        auctionResultMap.get(auctionName).updateLowestBidAmountIfItsFirstBid(bidAmount);
    }

    public static void addToMap(AuctionResult auctionResult) {
        auctionResultMap.put(auctionResult.getItem(), auctionResult);
    }

    public static void updateUserId(String currentItem, int userId) {
        auctionResultMap.get(currentItem).setUserId(userId);
    }

    public static void updateStatusToSold(String currentBidItem) {
        auctionResultMap.get(currentBidItem).updateStatusToSold();
    }

    public static void updatePricePaid(String currentItem, double reservePrice) {
        auctionResultMap.get(currentItem).updatePricePaid(reservePrice);
    }

    public static void updateHighestBid(String currentItem, double amount) {
        auctionResultMap.get(currentItem).setHighestBid(amount);
    }

    public static void updateTimeStamp(int timestamp) {
        for (AuctionResult auctionResult : auctionResultMap.values()) {
            auctionResult.setTimeStamp(timestamp);
        }
    }

    public static void updateBidCount(String currentBidForAuction) {
        auctionResultMap.get(currentBidForAuction).incrementTotalBidCount();
    }

    public static String returnOutput() {
        String auctionResultString = "";
        for (AuctionResult auctionResult : auctionResultMap.values()) {
            auctionResultString = auctionResultString
                    + auctionResult.toString()
                    + "\n";
        }
        return auctionResultString.substring(0, auctionResultString.length() - "\n".length());
    }
}
