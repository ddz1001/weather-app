package com.github.dantezitello.weatherapp.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class WeatherHistoryModel {

    BigDecimal latitude;
    BigDecimal longitude;

    @JsonProperty("daily")
    DailyTemperatureData dailyTemperatureData;

    @JsonProperty("daily_units")
    UnitData unitData;

    public WeatherHistoryModel() {
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public DailyTemperatureData getDailyTemperatureData() {
        return dailyTemperatureData;
    }

    public void setDailyTemperatureData(DailyTemperatureData dailyTemperatureData) {
        this.dailyTemperatureData = dailyTemperatureData;
    }

    public UnitData getUnitData() {
        return unitData;
    }

    public void setUnitData(UnitData unitData) {
        this.unitData = unitData;
    }
}
