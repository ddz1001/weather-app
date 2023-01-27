package com.github.dantezitello.weatherapp.weather;

import java.math.BigDecimal;

public class WeatherEntry {

    LocalDateRange interval;
    BigDecimal temperature;

    public WeatherEntry(LocalDateRange interval, BigDecimal highestTemperature) {
        this.interval = interval;
        this.temperature = highestTemperature;
    }

    public LocalDateRange getInterval() {
        return interval;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }
}
