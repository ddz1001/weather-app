package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChartRequestChartDetails {

    @JsonProperty("chart-grouping")
    ChartGroupingOption groupingOption;
    @JsonProperty("chart-style")
    ChartStyleOption styleOption;
    @JsonProperty("unit")
    ChartUnitOption unitOption;

    public ChartRequestChartDetails() {
    }

    public ChartGroupingOption getGroupingOption() {
        return groupingOption;
    }

    public void setGroupingOption(ChartGroupingOption groupingOption) {
        this.groupingOption = groupingOption;
    }

    public ChartStyleOption getStyleOption() {
        return styleOption;
    }

    public void setStyleOption(ChartStyleOption styleOption) {
        this.styleOption = styleOption;
    }

    public ChartUnitOption getUnitOption() {
        return unitOption;
    }

    public void setUnitOption(ChartUnitOption unitOption) {
        this.unitOption = unitOption;
    }
}
