package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ChartRequestDateRange {
    @JsonProperty("start-date")
    LocalDate startDate;
    @JsonProperty("end-date")
    LocalDate endDate;

    public ChartRequestDateRange() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
