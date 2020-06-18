package com.api.auctionhouseapi.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReadInputFileTest {
    @Test
    public void readFromInputFileTest() throws IOException {
        String path = "input.txt";
        ReadInputFile readInputFile = new ReadInputFile();
        assertEquals("10|1|SELL|toaster_1|10.00|20",readInputFile.readFromInputFile(path));
    }

    @Test
    public void readFromMultipleLinesInputFileTest() throws IOException {
        String path = "multipleLineInputFile.txt";
        ReadInputFile readInputFile = new ReadInputFile();
        assertEquals("10|1|SELL|toaster_1|10.00|20\n" +
                "12|8|BID|toaster_1|7.50\n" +
                "13|5|BID|toaster_1|12.50\n" +
                "15|8|SELL|tv_1|250.00|20\n" +
                "16\n" +
                "17|8|BID|toaster_1|20.00\n" +
                "18|1|BID|tv_1|150.00\n" +
                "19|3|BID|tv_1|200.00\n" +
                "20\n" +
                "21|3|BID|tv_1|300.00",readInputFile.readFromInputFile(path));
    }
}
