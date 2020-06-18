package com.api.auctionhouseapi.controllers;

import com.api.auctionhouseapi.AuctionHouse;
import com.api.auctionhouseapi.util.ParseInputRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@ComponentScan
public class ProcessAuction {
    ParseInputRecords parseInputRecords;
    AuctionHouse auctionHouse;
    List<Object> parseRecords;

    @Autowired
    public ProcessAuction(ParseInputRecords parseInputRecords, AuctionHouse auctionHouse) {
        this.parseInputRecords = parseInputRecords;
        this.auctionHouse = auctionHouse;
    }


    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String process(@RequestBody String inputString) {
        inputString = URLDecoder.decode(inputString);
        inputString = inputString.substring(0,inputString.length()-1);
            parseRecords = parseInputRecords.getParseRecords(inputString);
        return auctionHouse.process(parseRecords);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
