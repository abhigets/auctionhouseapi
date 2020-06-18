package com.api.auctionhouseapi.domain;

public class Heartbeat {
    private int timeStamp;
    static private int heartBeatCount = 0;

    public Heartbeat(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Heartbeat() {
    }

    public Object build(String currentItem) {
        timeStamp = Integer.parseInt(currentItem);
        return this;
    }

    public static boolean isHearbeatString(String currentItem) {
        try {
            Integer.parseInt(currentItem);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isHeartbeat(Object currentRecord) {
        try {
            Heartbeat heartbeat = (Heartbeat) currentRecord;
            if (heartbeat.timeStamp > 0)
                return true;
        } catch (ClassCastException ex) {
        }
        return false;
    }

    public static void incrementHeatBeatCount() {
        heartBeatCount++;
    }

    public boolean isLastHeartBeat(int count) {
        if (heartBeatCount == count)
            return true;
        return false;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public String toString() {
        return timeStamp + "";
    }
}
