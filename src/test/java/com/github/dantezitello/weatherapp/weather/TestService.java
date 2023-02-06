package com.github.dantezitello.weatherapp.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dantezitello.weatherapp.WeatherAppConfig;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;
import com.github.dantezitello.weatherapp.common.Interval;
import com.github.dantezitello.weatherapp.common.UnitType;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import org.junit.jupiter.api.*;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class TestService {

    static ClientAndServer mockBackEnd;
    static String response;
    WeatherHistoryService service;

    @Autowired
    ObjectMapper mapper;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new ClientAndServer(8080);
        response = Files.readString(Path.of("src/test/resources/yearly-data.json"));
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.stop();
    }

    @BeforeEach
    public void beforeEach() {
        WeatherAppConfig testCfg = new WeatherAppConfig();
        testCfg.setWeatherHistoryUrl("http://localhost:8080");
        service = new WeatherHistoryService(testCfg);
    }


    @Test
    public void testDaily() throws WeatherAPIException {

        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        WeatherHistoryResult result = service.fetchHistoryForLocation(
                new GeographicCoordinates(1.0,1.0),
                new WeatherHistoryOptions(UnitType.CELSIUS, Interval.DAILY),
                LocalDate.now(),
                LocalDate.now());

        assertNotEquals(0, result.getWeatherEntries().size());

    }

    @Test
    public void testWeekly() throws WeatherAPIException {

        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        WeatherHistoryResult result = service.fetchHistoryForLocation(
                new GeographicCoordinates(1.0,1.0),
                new WeatherHistoryOptions(UnitType.CELSIUS, Interval.WEEKLY),
                LocalDate.now(),
                LocalDate.now());

        assertNotEquals(0, result.getWeatherEntries().size());

    }

    @Test
    public void testMonthly() throws WeatherAPIException {

        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        WeatherHistoryResult result = service.fetchHistoryForLocation(
                new GeographicCoordinates(1.0,1.0),
                new WeatherHistoryOptions(UnitType.CELSIUS, Interval.MONTHLY),
                LocalDate.now(),
                LocalDate.now());

        assertNotEquals(0, result.getWeatherEntries().size());

    }

    @Test
    @Disabled
    public void testYearly() throws WeatherAPIException {

        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        WeatherHistoryResult result = service.fetchHistoryForLocation(
                new GeographicCoordinates(1.0,1.0),
                new WeatherHistoryOptions(UnitType.CELSIUS, Interval.YEARLY),
                LocalDate.now(),
                LocalDate.now().plusYears(2));

        assertNotEquals(0, result.getWeatherEntries().size());

    }


    @Test
    public void testDailyConvertToKelvin() throws WeatherAPIException {

        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        WeatherHistoryResult result = service.fetchHistoryForLocation(
                new GeographicCoordinates(1.0,1.0),
                new WeatherHistoryOptions(UnitType.KELVIN, Interval.DAILY),
                LocalDate.now(),
                LocalDate.now());

        assertNotEquals(0, result.getWeatherEntries().size());

    }





    public static MockServerClient mock() {
        return new MockServerClient("localhost", 8080);
    }




}
