package com.github.dantezitello.weatherapp.common;

import java.math.BigDecimal;

public class RecordedAverage {

    LocalDateRange interval;
    BigDecimal temperature;

    UnitType measurement;

    public RecordedAverage(LocalDateRange interval, BigDecimal temperature, UnitType measurement) {
        this.interval = interval;
        this.temperature = temperature;
        this.measurement = measurement;
    }

    public LocalDateRange getInterval() {
        return interval;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }
}
