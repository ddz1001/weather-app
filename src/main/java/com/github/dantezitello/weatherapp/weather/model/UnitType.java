package com.github.dantezitello.weatherapp.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UnitType {

    @JsonProperty("°C")
    CELSIUS("°C"),

    @JsonProperty("°F")
    FAHRENHEIT("°F"),

    @JsonProperty("°K")
    KELVIN("°K");

    UnitType(String symbol) {
        this.symbol = symbol;
    }

    private final String symbol;

    public String getSymbol() {
        return symbol;
    }
}
