package com.api.auctionhouseapi.util;

public class MyFormatter {
    public static String formatDecimalTo2Decimal(double value) {
        return String.format("%.2f", value);
    }
}