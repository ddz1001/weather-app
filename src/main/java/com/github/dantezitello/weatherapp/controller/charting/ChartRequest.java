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

package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ChartRequest {


    @JsonProperty(value = "chart-details", required = false)
    ChartRequestChartDetails details;

    @JsonProperty(value = "cities", required = true)
    List<ChartRequestCityInfo> cities;

    @JsonProperty(value = "date-range", required = true)
    ChartRequestDateRange dateRange;

    public ChartRequest() {
    }

    public boolean hasChartDetails() {
        return !Objects.isNull(details);
    }

    public ChartRequestChartDetails getDetails() {
        return details;
    }

    public void setDetails(ChartRequestChartDetails details) {
        this.details = details;
    }

    public List<ChartRequestCityInfo> getCities() {
        return cities;
    }

    public void setCities(List<ChartRequestCityInfo> cities) {
        this.cities = cities;
    }

    public ChartRequestDateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(ChartRequestDateRange dateRange) {
        this.dateRange = dateRange;
    }
}
