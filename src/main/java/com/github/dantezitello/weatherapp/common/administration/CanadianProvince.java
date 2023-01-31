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
