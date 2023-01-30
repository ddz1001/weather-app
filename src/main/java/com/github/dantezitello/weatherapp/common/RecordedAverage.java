package com.github.dantezitello.weatherapp.common;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public void setInterval(LocalDateRange interval) {
        this.interval = interval;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public UnitType getMeasurement() {
        return measurement;
    }

    public void setMeasurement(UnitType measurement) {
        this.measurement = measurement;
    }

    public static RecordedAverage record(String value, UnitType measurement, LocalDate start, LocalDate end) {
        return new RecordedAverage( LocalDateRange.from(start, end), new BigDecimal(value), measurement );
    }

    public static RecordedAverage record(String value, UnitType measurement, LocalDate date) {
        return new RecordedAverage( LocalDateRange.of(date), new BigDecimal(value), measurement );
    }
}
