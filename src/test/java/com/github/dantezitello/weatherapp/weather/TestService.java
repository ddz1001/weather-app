package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.common.GeographicCoordinates;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class TestService {


    @Autowired
    WeatherHistoryService service;


    @Test
    public void testFetch() throws WeatherAPIException {
        BigDecimal lat = new BigDecimal("40.40001");
        BigDecimal lon = new BigDecimal("-80.0");

        LocalDate start = LocalDate.parse("2020-12-20");
        LocalDate end = LocalDate.parse("2020-12-22");

        service.fetchHistoryForLocation(new GeographicCoordinates(lat, lon), start, end);
    }

}
