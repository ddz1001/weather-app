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

package com.github.dantezitello.weatherapp.common.administration;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum CanadianProvince implements AdministrationRegion{
    @JsonProperty("Alberta")                @JsonAlias({"AB"}) ALBERTA("AB","Alberta"),
    @JsonProperty("British Columbia")       @JsonAlias({"BC"}) BRITISH_COLUMBIA("BC","British Columbia"),
    @JsonProperty("Manitoba")               @JsonAlias({"MB"}) MANITOBA("MB","Manitoba"),
    @JsonProperty("New Brunswick")          @JsonAlias({"NB"}) NEW_BRUNSWICK("NB","New Brunswick"),
    @JsonProperty("Newfoundland and Labrador") @JsonAlias({"NL"}) NEWFOUNDLAND_AND_LABRADOR("NL","Newfoundland and Labrador"),
    @JsonProperty("Northwest Territories")  @JsonAlias({"NT"}) NORTHWEST_TERRITORIES("NT","Northwest Territories"),
    @JsonProperty("Nova Scotia")            @JsonAlias({"NS"}) NOVA_SCOTIA("NS","Nova Scotia"),
    @JsonProperty("Nunavut")                @JsonAlias({"NU"}) NUNAVUT("NU","Nunavut"),
    @JsonProperty("Ontario")                @JsonAlias({"ON"}) ONTARIO("ON","Ontario"),
    @JsonProperty("Prince Edward Island")   @JsonAlias({"PE"}) PRINCE_EDWARD_ISLAND("PE","Prince Edward Island"),
    @JsonProperty("Quebec")                 @JsonAlias({"QC"}) QUEBEC("QC","Quebec"),
    @JsonProperty("Saskatchewan")           @JsonAlias({"SK"}) SASKATCHEWAN("SK","Saskatchewan"),
    @JsonProperty("Yukon")                  @JsonAlias({"YT"}) YUKON("YT","Yukon")
            ;

    private final String abbreviation;
    private final String englishName;

    CanadianProvince(String abbreviation, String englishName) {
        this.abbreviation = abbreviation;
        this.englishName = englishName;
    }

    @Override
    public String abbreviation() {
        return abbreviation;
    }

    @Override
    public String englishName() {
        return englishName;
    }
}
