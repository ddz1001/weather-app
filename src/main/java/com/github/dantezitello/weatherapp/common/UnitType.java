/*
 * Weather Charting Project
 * Copyright (C) 2023 Dante Zitello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.dantezitello.weatherapp.common;

import com.fasterxml.jackson.annotation.JsonAlias;
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
