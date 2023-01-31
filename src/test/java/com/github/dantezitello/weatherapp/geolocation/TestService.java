package com.github.dantezitello.weatherapp.geolocation;

import com.github.dantezitello.weatherapp.WeatherAppConfig;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import com.github.dantezitello.weatherapp.common.administration.Country;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestService {


    static ClientAndServer mockBackEnd;
    static String response;

    GeolocationService service;



    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new ClientAndServer(8080);
        response = Files.readString(Path.of("src/test/resources/city-of-chester.json"));
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.stop();
    }

    @BeforeEach
    void beforeEach() {
        WeatherAppConfig config = new WeatherAppConfig();
        config.setGeolocationUrl("http://localhost:8080");
        service = new GeolocationService(config);
    }

    public static MockServerClient mock() {
        return new MockServerClient("localhost", 8080);
    }




    @Test
    public void testNameOnly() throws WeatherAPIException {
        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        GeolocationResult result = service.find("chester");

        assertEquals("Chester", result.getCityInfo().getCityName());
        assertEquals(Country.UNITED_KINGDOM, result.getCityInfo().getCountry());
        assertEquals("England", result.getCityInfo().getAdministrativeRegion());
    }

    @Test
    public void testNameCountry() throws WeatherAPIException {
        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        GeolocationResult result = service.find("chester", Country.UNITED_STATES);

        assertEquals("Chester", result.getCityInfo().getCityName());
        assertEquals(Country.UNITED_STATES, result.getCityInfo().getCountry());
        assertEquals("Illinois", result.getCityInfo().getAdministrativeRegion());
    }

    @Test
    public void testNameCountryState() throws WeatherAPIException {
        MockServerClient serverClient = mock();

        serverClient.when( HttpRequest.request().withMethod("GET").withBody("") ).respond(
                HttpResponse.response(response).withHeader("Content-Type", "application/json")
        );

        GeolocationResult result = service.find("chester", Country.UNITED_STATES, "montana");

        assertEquals("Chester", result.getCityInfo().getCityName());
        assertEquals(Country.UNITED_STATES, result.getCityInfo().getCountry());
        assertEquals("Montana", result.getCityInfo().getAdministrativeRegion());
    }
}
