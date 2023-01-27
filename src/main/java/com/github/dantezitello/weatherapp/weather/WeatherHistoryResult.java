package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.common.RecordedAverage;

import java.util.ArrayList;
import java.util.List;

public class WeatherHistoryResult {

    List<RecordedAverage> weatherEntries;

    public WeatherHistoryResult() {
        weatherEntries = new ArrayList<>();
    }


    public List<RecordedAverage> getWeatherEntries() {
        return weatherEntries;
    }


}
