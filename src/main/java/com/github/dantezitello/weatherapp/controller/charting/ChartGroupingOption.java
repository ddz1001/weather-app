package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChartGroupingOption {

    @JsonProperty("daily") DAILY,
    @JsonProperty("weekly") WEEKLY,
    @JsonProperty("monthly") MONTHLY,
    @JsonProperty("yearly") YEARLY

}
