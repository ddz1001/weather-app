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

    @Override
    public String toString() {
        return "RecordedAverage{" +
                "interval=" + interval +
                ", temperature=" + temperature +
                ", measurement=" + measurement +
                '}';
    }
}
