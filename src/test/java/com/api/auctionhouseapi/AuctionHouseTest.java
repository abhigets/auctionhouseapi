package com.api.auctionhouseapi;

import com.api.auctionhouseapi.util.ParseInputRecords;
import com.api.auctionhouseapi.util.ReadInputFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuctionHouseTest {

    @Test
    public void verifyAuctionForValidInputTest() throws IOException {
        ReadInputFile readInputFile = new ReadInputFile();
        String inputRecords = readInputFile.readFromInputFile("multipleLineInputFile.txt");
        ParseInputRecords parseInputRecords = new ParseInputRecords();
        List<Object> parseRecords = parseInputRecords.getParseRecords(inputRecords);

        AuctionHouse auctionHouse = new AuctionHouse();
        String auctionOutput = auctionHouse.process(parseRecords);
        String expectedAuctionOutput = "20|tv_1||UNSOLD|0.00|2|200.00|150.00\n"
                + "20|toaster_1|8|SOLD|12.50|3|20.00|7.50" ;
        Assert.assertEquals(expectedAuctionOutput,auctionOutput);
    }
}
