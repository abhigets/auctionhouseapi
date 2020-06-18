package com.api.auctionhouseapi.util;

import com.api.auctionhouseapi.domain.Bid;
import com.api.auctionhouseapi.domain.Heartbeat;
import com.api.auctionhouseapi.domain.Sell;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParseInputFileTest {

    @Test
    public void verifyValidInputWillCanBeParse() throws IOException {
        String inputRecords = "10|1|SELL|toaster_1|10.00|20\n" +
                "12|8|BID|toaster_1|7.50\n" +
                "13|5|BID|toaster_1|12.50\n" +
                "15|8|SELL|tv_1|250.00|20\n" +
                "16\n" +
                "17|8|BID|toaster_1|20.00\n" +
                "18|1|BID|tv_1|150.00\n" +
                "19|3|BID|tv_1|200.00\n" +
                "20\n" +
                "21|3|BID|tv_1|300.00";
        ParseInputRecords parseInputRecords = new ParseInputRecords();
        List<Object> actualObjectList = parseInputRecords.getParseRecords(inputRecords);
        List<Object> expectedObjectList = buildExpectedObjectList();
        assertEquals(expectedObjectList.toString().trim(), actualObjectList.toString().trim());
    }

    @Test
    public void verifyInValidInputWillCannotBeParse() throws IOException {
        String inputRecords = "10|1|SELL|toaster_1|!!!!|20\n" +
                "12|BID|toaster_1|7.50\n" +
                "16aa";
        ParseInputRecords parseInputRecords = new ParseInputRecords();
        List<Object> actualObjectList = parseInputRecords.getParseRecords(inputRecords);
        assertEquals("[]", actualObjectList.toString().trim());
    }


    private List<Object> buildExpectedObjectList(){
        List<Object> expectedObjectList = new ArrayList<Object>();
        Sell toasterSell = new Sell(10,1,"SELL","toaster_1",10.00,20);
        expectedObjectList.add(toasterSell);
        Bid toasterBid1 = new Bid(12,8,"BID","toaster_1",7.50);
        expectedObjectList.add(toasterBid1);
        Bid toasterBid2 = new Bid(13,5,"BID","toaster_1",12.50);
        expectedObjectList.add(toasterBid2);
        Sell tvSell = new Sell(15,8,"SELL","tv_1",250.00,20);
        expectedObjectList.add(tvSell);
        Heartbeat heartbeat = new Heartbeat(16);
        expectedObjectList.add(heartbeat);
        Bid toasterBid3 = new Bid(17,8,"BID","toaster_1",20.00);
        expectedObjectList.add(toasterBid3);
        Bid tvBid1 = new Bid(18,1,"BID","tv_1",150.00);
        expectedObjectList.add(tvBid1);
        Bid tvBid2 = new Bid(19,3,"BID","tv_1",200.00);
        expectedObjectList.add(tvBid2);
        heartbeat = new Heartbeat(20);
        expectedObjectList.add(heartbeat);
        Bid tvBid3 = new Bid(21,3,"BID","tv_1",300.00);
        expectedObjectList.add(tvBid3);
        return expectedObjectList;
    }
}
