package com.github.dantezitello.weatherapp.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyTemperatureData {

    @JsonProperty("time")
    List<LocalDate> timeEntries;

    @JsonProperty("temperature_2m_max")
    List<BigDecimal> temperatureEntries;

    public DailyTemperatureData() {
        timeEntries = new ArrayList<LocalDate>();
        temperatureEntries = new ArrayList<>();
    }

    public List<LocalDate> getTimeEntries() {
        return timeEntries;
    }

    public void setTimeEntries(List<LocalDate> timeEntries) {
        this.timeEntries = timeEntries;
    }

    public List<BigDecimal> getTemperatureEntries() {
        return temperatureEntries;
    }

    public void setTemperatureEntries(List<BigDecimal> temperatureEntries) {
        this.temperatureEntries = temperatureEntries;
    }
}
