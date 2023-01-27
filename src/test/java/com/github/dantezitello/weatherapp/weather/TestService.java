package com.github.dantezitello.weatherapp.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dantezitello.weatherapp.WeatherAPIConfig;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import com.github.dantezitello.weatherapp.weather.model.WeatherHistoryModel;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestService {

    static MockWebServer mockBackEnd;
    static String response;
    WeatherHistoryService service;

    @Autowired
    ObjectMapper mapper;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start(8080);

        response = Files.readString(Path.of("src/test/resources/weekly-data.json"));
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    public void beforeEach() {
        WeatherAPIConfig testCfg = new WeatherAPIConfig();
        testCfg.setWeatherHistoryUrl("http://localhost:" + mockBackEnd.getPort());
        service = new WeatherHistoryService(testCfg);
    }


    @Test
    public void testDaily() throws WeatherAPIException, InterruptedException, JsonProcessingException {
        mockBackEnd.enqueue(new MockResponse().setBody(response).setHeader("Content-Type", "application/json").setResponseCode(200).setBodyDelay(1, TimeUnit.MILLISECONDS));

        WeatherHistoryResult result = service.fetchHistoryForLocation(new GeographicCoordinates(1.0,1.0),
                WeatherHistoryService.AggregationOption.DAILY,
                LocalDate.now(),
                LocalDate.now());

        result.getWeatherEntries().size();
    }








}
