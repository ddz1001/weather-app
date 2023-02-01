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

package com.github.dantezitello.weatherapp.graphics;

import com.github.dantezitello.weatherapp.common.ChartStyling;
import com.github.dantezitello.weatherapp.common.ContentGenerationType;
import com.github.dantezitello.weatherapp.common.Interval;
import com.github.dantezitello.weatherapp.common.UnitType;

public class ChartOptions {

    ChartStyling displayType;
    Interval intervalType;
    ContentGenerationType contentType;
    UnitType unitType;


    public ChartOptions(ChartStyling displayType, Interval intervalType, ContentGenerationType contentType, UnitType unitType) {
        this.displayType = displayType;
        this.intervalType = intervalType;
        this.contentType = contentType;
        this.unitType = unitType;
    }

    public ChartStyling getDisplayType() {
        return displayType;
    }

    public Interval getIntervalType() {
        return intervalType;
    }

    public ContentGenerationType getContentType() {
        return contentType;
    }

    public UnitType getUnitType() {
        return unitType;
    }
}
