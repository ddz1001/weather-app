package com.github.dantezitello.weatherapp.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dantezitello.weatherapp.common.RecordedAverage;
import com.github.dantezitello.weatherapp.weather.model.WeatherHistoryModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTranslator {

    final static ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @Test
    public void testWeekly() throws IOException {
        String json = Files.readString(Path.of("src/test/resources/weekly-data.json"));

        WeatherHistoryModel model = mapper.readValue(json, WeatherHistoryModel.class);

        WeatherResultAggregation translator = new WeatherResultAggregation();
        WeatherHistoryResult result = translator.aggregateWeekly(model);

        List<RecordedAverage> weatherEntryList = result.getWeatherEntries();

        assertEquals(14, weatherEntryList.size()); //14 total weeks

        LocalDate expectedStart = LocalDate.parse("2021-12-27");
        LocalDate expectedEnd = LocalDate.parse("2022-04-03");
        LocalDate weekend = LocalDate.parse("2022-01-02"); //this is to verify the interval width


        assertEquals(expectedStart, weatherEntryList.get(0).getInterval().getStart());
        assertEquals(weekend, weatherEntryList.get(0).getInterval().getEnd());
        assertEquals(expectedEnd, weatherEntryList.get( weatherEntryList.size() -1).getInterval().getEnd());
    }

    @Test
    public void testMonthly() throws IOException {
        String json = Files.readString(Path.of("src/test/resources/monthly-data.json"));

        WeatherHistoryModel model = mapper.readValue(json, WeatherHistoryModel.class);

        WeatherResultAggregation translator = new WeatherResultAggregation();
        WeatherHistoryResult result = translator.aggregateMonthly(model);
        List<RecordedAverage> weatherEntryList = result.getWeatherEntries();

        assertEquals(3, weatherEntryList.size());

        LocalDate expectedStart = LocalDate.parse("2022-10-01");
        LocalDate expectedEnd = LocalDate.parse("2022-12-31");
        LocalDate monthend = LocalDate.parse("2022-10-31"); //this is to verify the interval width


        assertEquals(expectedStart, weatherEntryList.get(0).getInterval().getStart());
        assertEquals(monthend, weatherEntryList.get(0).getInterval().getEnd());
        assertEquals(expectedEnd, weatherEntryList.get( weatherEntryList.size() -1).getInterval().getEnd());



    }

    @Test
    public void testYearly() throws IOException {
        String json = Files.readString(Path.of("src/test/resources/yearly-data.json"));

        WeatherHistoryModel model = mapper.readValue(json, WeatherHistoryModel.class);

        WeatherResultAggregation translator = new WeatherResultAggregation();
        WeatherHistoryResult result = translator.aggregateYearly(model);
        List<RecordedAverage> weatherEntryList = result.getWeatherEntries();

        assertEquals(3, weatherEntryList.size());

        LocalDate expectedStart = LocalDate.parse("2019-01-01");
        LocalDate expectedEnd = LocalDate.parse("2021-12-31");
        LocalDate yearend = LocalDate.parse("2019-12-31"); //this is to verify the interval width

        assertEquals(expectedStart, weatherEntryList.get(0).getInterval().getStart());
        assertEquals(yearend, weatherEntryList.get(0).getInterval().getEnd());
        assertEquals(expectedEnd, weatherEntryList.get( weatherEntryList.size() -1).getInterval().getEnd());

    }




}
