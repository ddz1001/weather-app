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

package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.common.Interval;
import com.github.dantezitello.weatherapp.common.UnitType;

public class WeatherHistoryOptions {

    UnitType type;
    Interval interval;

    public WeatherHistoryOptions(UnitType type, Interval interval) {
        this.type = type;
        this.interval = interval;
    }

    public UnitType getType() {
        return type;
    }

    public Interval getInterval() {
        return interval;
    }

    public static WeatherHistoryOptions getDefault() {
        return new WeatherHistoryOptions(UnitType.CELSIUS, Interval.DAILY);
    }


}
