package com.github.dantezitello.weatherapp.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dantezitello.weatherapp.weather.model.UnitType;
import com.github.dantezitello.weatherapp.weather.model.WeatherHistoryModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModel {


    final static String sampleJson = "{\n" +
            "    \"latitude\": 40.40001,\n" +
            "    \"longitude\": -80.0,\n" +
            "    \"generationtime_ms\": 0.2709627151489258,\n" +
            "    \"utc_offset_seconds\": 0,\n" +
            "    \"timezone\": \"GMT\",\n" +
            "    \"timezone_abbreviation\": \"GMT\",\n" +
            "    \"elevation\": 247.0,\n" +
            "    \"daily_units\": {\n" +
            "        \"time\": \"iso8601\",\n" +
            "        \"temperature_2m_max\": \"°C\"\n" +
            "    },\n" +
            "    \"daily\": {\n" +
            "        \"time\": [\n" +
            "            \"2020-12-20\",\n" +
            "            \"2020-12-21\",\n" +
            "            \"2020-12-22\"\n" +
            "        ],\n" +
            "        \"temperature_2m_max\": [\n" +
            "            2.8,\n" +
            "            5.3,\n" +
            "            5.9\n" +
            "        ]\n" +
            "    }\n" +
            "}";


    @Test
    public void testDeserialization() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        WeatherHistoryModel model = mapper.readValue( sampleJson, WeatherHistoryModel.class );

        assertEquals(UnitType.CELSIUS, model.getUnitData().getUnitType());
        assertEquals(3, model.getDailyTemperatureData().getTemperatureEntries().size());
        assertEquals(3, model.getDailyTemperatureData().getTimeEntries().size());
        assertEquals(new BigDecimal("40.40001"), model.getLatitude());
        assertEquals(new BigDecimal("-80.0"), model.getLongitude());

    }

}
