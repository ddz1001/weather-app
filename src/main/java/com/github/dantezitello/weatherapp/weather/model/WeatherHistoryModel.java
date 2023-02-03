/*
 * Weather Charting Project
 * Copyright (C) 2023 Dante Zitello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.dantezitello.weatherapp.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dantezitello.weatherapp.common.LocalDateRange;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
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
