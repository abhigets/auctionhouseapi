package com.api.auctionhouseapi.controllers;

import com.api.auctionhouseapi.AuctionHouse;
import com.api.auctionhouseapi.util.ParseInputRecords;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessAuctionTest {

    @Mock
    ParseInputRecords parseInputRecords;
    @Mock
    AuctionHouse auctionHouse;

    @InjectMocks
    ProcessAuction processAuction;

    @Test
    public void verifyIfAllFunctionWereCalledInSequence() {
        String input = "10|1|SELL|toaster_1|10.00|20\n" +
                "12|8|BID|toaster_1|7.50\n" +
                "13|5|BID|toaster_1|12.50\n" +
                "15|8|SELL|tv_1|250.00|20\n" +
                "16\n" +
                "17|8|BID|toaster_1|20.00\n" +
                "18|1|BID|tv_1|150.00\n" +
                "19|3|BID|tv_1|200.00\n" +
                "20\n" +
                "21|3|BID|tv_1|300.00";
        String input2 = "10|1|SELL|toaster_1|10.00|20\n" +
                "12|8|BID|toaster_1|7.50\n" +
                "13|5|BID|toaster_1|12.50\n" +
                "15|8|SELL|tv_1|250.00|20\n" +
                "16\n" +
                "17|8|BID|toaster_1|20.00\n" +
                "18|1|BID|tv_1|150.00\n" +
                "19|3|BID|tv_1|200.00\n" +
                "20\n" +
                "21|3|BID|tv_1|300.0";
        processAuction.process(input);
        verify(parseInputRecords,times(1)).getParseRecords(input2);
        verify(auctionHouse,times(1)).process(new ArrayList<>());
    }
}
