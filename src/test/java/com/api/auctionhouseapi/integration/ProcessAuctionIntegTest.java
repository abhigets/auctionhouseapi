package com.api.auctionhouseapi.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProcessAuctionIntegTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTheOutputOfAuction() throws Exception {
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


                        this.mockMvc.perform(post("/process").content(input))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("0|tv_1||UNSOLD|0.00|3|300.00|150.00\n" +
                        "0|toaster_1|8|SOLD|12.50|3|20.00|7.50")));
    }
}
