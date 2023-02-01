package com.github.dantezitello.weatherapp.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum UnitType {

    @JsonProperty("°C") @JsonAlias({"Celsius", "C" })
    CELSIUS("°C", "Celsius"),

    @JsonProperty("°F") @JsonAlias({"Fahrenheit", "F" })
    FAHRENHEIT("°F", "Fahrenheit"),

    @JsonProperty("°K") @JsonAlias({"Kelvin", "K"})
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

    public String getAPIName() {
        return fullName.toLowerCase();
    }
}
