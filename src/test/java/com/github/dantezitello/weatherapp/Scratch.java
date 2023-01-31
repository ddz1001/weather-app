package com.github.dantezitello.weatherapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dantezitello.weatherapp.controller.ChartRequestController;
import com.github.dantezitello.weatherapp.controller.charting.ChartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Scratch {

    @Autowired
    ObjectMapper mapper;

    String msg ="{\n" +
            "    \"chart-details\":{\n" +
            "        \"chart-grouping\":\"daily\",\n" +
            "        \"chart-style\":\"bar\",\n" +
            "        \"unit\":\"celsius\"\n" +
            "    },\n" +
            "\n" +
            "    \"cities\" : [\n" +
            "        {\n" +
            "            \"city-name\":\"Pittsburgh\"\n" +
            "        }\n" +
            "    ],\n" +
            "\n" +
            "    \"date-range\":{\n" +
            "        \"start-date\":\"2020-01-01\",\n" +
            "        \"end-date\":\"2020-01-10\"\n" +
            "    }\n" +
            "}";

    @Test
    public void ex() throws JsonProcessingException {
        mapper.readValue(msg, ChartRequest.class);
    }
}
