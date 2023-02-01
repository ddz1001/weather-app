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
