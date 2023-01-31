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
