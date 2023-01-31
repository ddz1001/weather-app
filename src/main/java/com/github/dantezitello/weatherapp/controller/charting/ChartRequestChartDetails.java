package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dantezitello.weatherapp.common.ChartStyling;
import com.github.dantezitello.weatherapp.common.Interval;
import com.github.dantezitello.weatherapp.common.UnitType;

public class ChartRequestChartDetails {

    @JsonProperty("chart-interval")
    Interval groupingOption;
    @JsonProperty("chart-style")
    ChartStyling styleOption;
    @JsonProperty("unit")
    UnitType unitOption;

    public ChartRequestChartDetails() {
    }

    public Interval getGroupingOption() {
        return groupingOption;
    }

    public void setGroupingOption(Interval groupingOption) {
        this.groupingOption = groupingOption;
    }

    public ChartStyling getStyleOption() {
        return styleOption;
    }

    public void setStyleOption(ChartStyling styleOption) {
        this.styleOption = styleOption;
    }

    public UnitType getUnitOption() {
        return unitOption;
    }

    public void setUnitOption(UnitType unitOption) {
        this.unitOption = unitOption;
    }
}
