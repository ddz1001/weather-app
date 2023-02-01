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
import com.github.dantezitello.weatherapp.common.administration.CanadianProvince;
import com.github.dantezitello.weatherapp.common.administration.Country;
import com.github.dantezitello.weatherapp.common.administration.MexicanState;
import com.github.dantezitello.weatherapp.common.administration.UnitedStatesState;

import java.util.Objects;

public class ChartRequestCityInfo {

    @JsonProperty(value = "city-name", required = true)
    String cityName;
    @JsonProperty("country")
    Country country;
    @JsonProperty("usa-state")
    UnitedStatesState usaState;
    @JsonProperty("can-province")
    CanadianProvince canProvince;
    @JsonProperty("mex-state")
    MexicanState mexState;
    @JsonProperty("admin-region")
    String adminRegion;

    public ChartRequestCityInfo() {
    }

    public boolean isUSA() {
        return Objects.nonNull(usaState);
    }

    public boolean isCAN() {
        return Objects.nonNull( canProvince );
    }

    public boolean isMEX() {
        return Objects.nonNull( mexState );
    }

    public boolean hasAdmin() {
        return Objects.nonNull(adminRegion);
    }

    public boolean hasCountry() {
        return Objects.nonNull(country);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public UnitedStatesState getUsaState() {
        return usaState;
    }

    public void setUsaState(UnitedStatesState usaState) {
        this.usaState = usaState;
    }

    public CanadianProvince getCanProvince() {
        return canProvince;
    }

    public void setCanProvince(CanadianProvince canProvince) {
        this.canProvince = canProvince;
    }

    public MexicanState getMexState() {
        return mexState;
    }

    public void setMexState(MexicanState mexState) {
        this.mexState = mexState;
    }

    public String getAdminRegion() {
        return adminRegion;
    }

    public void setAdminRegion(String adminRegion) {
        this.adminRegion = adminRegion;
    }
}
