package com.github.dantezitello.weatherapp.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum UnitType {

    @JsonProperty("°C") @JsonAlias("Celsius")
    CELSIUS("°C", "Celsius"),

    @JsonProperty("°F") @JsonAlias("Fahrenheit")
    FAHRENHEIT("°F", "Fahrenheit"),

    @JsonProperty("°K") @JsonAlias("Kelvin")
    KELVIN("°K", "Kelvin");

    UnitType(String symbol, String fullName) {
        this.symbol = symbol;
        this.fullName = fullName;
    }

    private final String symbol;
    private final String fullName;

    public String getSymbol() {
        return symbol;
    }

    public String getFullName() {
        return fullName;
    }
}
