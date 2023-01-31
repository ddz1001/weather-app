package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChartUnitOption {
    @JsonProperty("celsius") CELSIUS,
    @JsonProperty("fahrenheit") FAHRENHEIT,
    @JsonProperty("kelvin") KELVIN
}
