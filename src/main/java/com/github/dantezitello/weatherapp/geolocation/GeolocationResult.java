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

package com.github.dantezitello.weatherapp.geolocation;

import com.github.dantezitello.weatherapp.common.CityInfo;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;

public class GeolocationResult {
    CityInfo cityInfo;
    GeographicCoordinates coordinates;

    public GeolocationResult(CityInfo cityInfo, GeographicCoordinates coordinates) {
        this.cityInfo = cityInfo;
        this.coordinates = coordinates;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public GeographicCoordinates getCoordinates() {
        return coordinates;
    }
}
