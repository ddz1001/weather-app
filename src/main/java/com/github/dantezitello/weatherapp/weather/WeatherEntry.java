package com.github.dantezitello.weatherapp.weather;

import java.math.BigDecimal;

public class WeatherEntry {

    WeatherInterval interval;
    BigDecimal temperature;

    public WeatherEntry(WeatherInterval interval, BigDecimal highestTemperature) {
        this.interval = interval;
        this.temperature = highestTemperature;
    }

    public WeatherInterval getInterval() {
        return interval;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }
}
