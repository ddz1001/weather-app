package com.github.dantezitello.weatherapp.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherHistoryResult {

    List<WeatherEntry> weatherEntries;

    public WeatherHistoryResult() {
        weatherEntries = new ArrayList<>();
    }


    public List<WeatherEntry> getWeatherEntries() {
        return weatherEntries;
    }


}
