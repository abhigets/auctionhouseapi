package com.api.auctionhouseapi;

import com.api.auctionhouseapi.domain.AuctionResult;
import com.api.auctionhouseapi.domain.Bid;
import com.api.auctionhouseapi.domain.Heartbeat;
import com.api.auctionhouseapi.domain.Sell;

public class ProcessAuction {
    public void processIfSale(Object object) {
        if (Sell.isAuctionString(object)) {
            Sell sell = (Sell) object;
            AuctionMap.addToMap(sell);
            AuctionResult auctionResult = new AuctionResult(0,
                    sell.getItem(),
                    0,
                    "UNSOLD",
                    0.00,
                    0,
                    0.0,
                    0.0
            );
            AuctionResultMap.addToMap(auctionResult);
        }
    }

    public void processIfBid(Object object) {
        if (!Bid.isStringBid(object))
            return;

        Bid bid = (Bid) object;
        String currentBidForAuction = bid.getItem();
        if (!AuctionMap.isAuctionPresent(currentBidForAuction))
            return;

        AuctionResultMap.updateLowestBidAmountIfItsFirstBid(currentBidForAuction, bid.getBidAmount());

        Sell sell = AuctionMap.getAuction(currentBidForAuction);
        if (bid.isBidValid(
                sell.getTimeStamp(),
                sell.getCloseTime(),
                AuctionResultMap.getAuctionFrom(currentBidForAuction).getHighestBid(),
                sell.getReservePrice())) {

            AuctionResultMap.updateUserId(currentBidForAuction, bid.getUserId());
            AuctionResultMap.updateStatusToSold(currentBidForAuction);
            AuctionResultMap.updatePricePaid(currentBidForAuction, sell.getReservePrice());
        }
        AuctionResultMap.updateHighestBid(currentBidForAuction, bid.getBidAmount());
        AuctionResultMap.updateBidCount(currentBidForAuction);
    }

    public void processIfHeartbeat(Object object) {
        if (!Heartbeat.isHeartbeat(object))
            return;
        Heartbeat.incrementHeatBeatCount();
        Heartbeat heartbeat = (Heartbeat) object;
        if (heartbeat.isLastHeartBeat(AuctionMap.getCount())) {
            AuctionResultMap.updateTimeStamp(heartbeat.getTimeStamp());
            AuctionMap.removeAllAuction();
        }
    }

    public String toString() {
        return AuctionResultMap.returnOutput();
    }
}
