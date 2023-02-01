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
