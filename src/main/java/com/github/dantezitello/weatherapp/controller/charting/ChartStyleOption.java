package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChartStyleOption {
    @JsonProperty("line") LINE,
    @JsonProperty("bar") BAR
}
