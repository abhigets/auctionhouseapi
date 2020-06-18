package com.api.auctionhouseapi;

import com.api.auctionhouseapi.domain.Sell;
import java.util.HashMap;
import java.util.Map;

public class AuctionMap {
    private static Map<String, Sell> saleMap = new HashMap<String, Sell>();

    public static boolean isAuctionPresent(String itemName) {
        return saleMap.containsKey(itemName);
    }

    public static Sell getAuction(String itemName) {
        return saleMap.get(itemName);
    }

    public static void addToMap(Sell sell) {
        saleMap.put(sell.getItem(), sell);
    }

    public static int getCount() {
        return saleMap.size();
    }

    public static void removeAllAuction() {
        saleMap.clear();
    }
}
