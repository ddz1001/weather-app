package com.github.dantezitello.weatherapp.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DailyTemperatureData {

    @JsonProperty("daily")
    List<String> timeEntries;

    @JsonProperty("temperature_2m")
    List<BigDecimal> temperatureEntries;

    public DailyTemperatureData() {
        timeEntries = new ArrayList<>();
        temperatureEntries = new ArrayList<>();
    }

    public List<String> getTimeEntries() {
        return timeEntries;
    }

    public void setTimeEntries(List<String> timeEntries) {
        this.timeEntries = timeEntries;
    }

    public List<BigDecimal> getTemperatureEntries() {
        return temperatureEntries;
    }

    public void setTemperatureEntries(List<BigDecimal> temperatureEntries) {
        this.temperatureEntries = temperatureEntries;
    }
}
