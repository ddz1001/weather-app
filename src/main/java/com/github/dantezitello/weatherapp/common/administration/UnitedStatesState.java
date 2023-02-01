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

public enum UnitedStatesState implements AdministrationRegion {
    //This file was generated using excel and data from the ISO website


    @JsonProperty("Alabama")                @JsonAlias({"AL"}) ALABAMA("AL","Alabama"),
    @JsonProperty("Alaska")                 @JsonAlias({"AK"}) ALASKA("AK","Alaska"),
    @JsonProperty("Arizona")                @JsonAlias({"AZ"}) ARIZONA("AZ","Arizona"),
    @JsonProperty("Arkansas")               @JsonAlias({"AR"}) ARKANSAS("AR","Arkansas"),
    @JsonProperty("American Samoa")         @JsonAlias({"AS"}) AMERICAN_SAMOA("AS","American Samoa"),
    @JsonProperty("California")             @JsonAlias({"CA"}) CALIFORNIA("CA","California"),
    @JsonProperty("Colorado")               @JsonAlias({"CO"}) COLORADO("CO","Colorado"),
    @JsonProperty("Connecticut")            @JsonAlias({"CT"}) CONNECTICUT("CT","Connecticut"),
    @JsonProperty("Delaware")               @JsonAlias({"DE"}) DELAWARE("DE","Delaware"),
    @JsonProperty("District of Columbia")   @JsonAlias({"DC"}) DISTRICT_OF_COLUMBIA("DC","District of Columbia"),
    @JsonProperty("Florida")                @JsonAlias({"FL"}) FLORIDA("FL","Florida"),
    @JsonProperty("Georgia")                @JsonAlias({"GA"}) GEORGIA("GA","Georgia"),
    @JsonProperty("Guam")                   @JsonAlias({"GU"}) GUAM("GU","Guam"),
    @JsonProperty("Hawaii")                 @JsonAlias({"HI"}) HAWAII("HI","Hawaii"),
    @JsonProperty("Idaho")                  @JsonAlias({"ID"}) IDAHO("ID","Idaho"),
    @JsonProperty("Illinois")               @JsonAlias({"IL"}) ILLINOIS("IL","Illinois"),
    @JsonProperty("Indiana")                @JsonAlias({"IN"}) INDIANA("IN","Indiana"),
    @JsonProperty("Iowa")                   @JsonAlias({"IA"}) IOWA("IA","Iowa"),
    @JsonProperty("Kansas")                 @JsonAlias({"KS"}) KANSAS("KS","Kansas"),
    @JsonProperty("Kentucky")               @JsonAlias({"KY"}) KENTUCKY("KY","Kentucky"),
    @JsonProperty("Louisiana")              @JsonAlias({"LA"}) LOUISIANA("LA","Louisiana"),
    @JsonProperty("Maine")                  @JsonAlias({"ME"}) MAINE("ME","Maine"),
    @JsonProperty("Maryland")               @JsonAlias({"MD"}) MARYLAND("MD","Maryland"),
    @JsonProperty("Massachusetts")          @JsonAlias({"MA"}) MASSACHUSETTS("MA","Massachusetts"),
    @JsonProperty("Michigan")               @JsonAlias({"MI"}) MICHIGAN("MI","Michigan"),
    @JsonProperty("Minnesota")              @JsonAlias({"MN"}) MINNESOTA("MN","Minnesota"),
    @JsonProperty("Mississippi")            @JsonAlias({"MS"}) MISSISSIPPI("MS","Mississippi"),
    @JsonProperty("Missouri")               @JsonAlias({"MO"}) MISSOURI("MO","Missouri"),
    @JsonProperty("Montana")                @JsonAlias({"MT"}) MONTANA("MT","Montana"),
    @JsonProperty("Nebraska")               @JsonAlias({"NE"}) NEBRASKA("NE","Nebraska"),
    @JsonProperty("Nevada")                 @JsonAlias({"NV"}) NEVADA("NV","Nevada"),
    @JsonProperty("New Hampshire")          @JsonAlias({"NH"}) NEW_HAMPSHIRE("NH","New Hampshire"),
    @JsonProperty("New Jersey")             @JsonAlias({"NJ"}) NEW_JERSEY("NJ","New Jersey"),
    @JsonProperty("New Mexico")             @JsonAlias({"NM"}) NEW_MEXICO("NM","New Mexico"),
    @JsonProperty("New York")               @JsonAlias({"NY"}) NEW_YORK("NY","New York"),
    @JsonProperty("North Carolina")         @JsonAlias({"NC"}) NORTH_CAROLINA("NC","North Carolina"),
    @JsonProperty("North Dakota")           @JsonAlias({"ND"}) NORTH_DAKOTA("ND","North Dakota"),
    @JsonProperty("Northern Mariana Islands") @JsonAlias({"MP"}) NORTHERN_MARIANA_ISLANDS("MP","Northern Mariana Islands"),
    @JsonProperty("Ohio")                   @JsonAlias({"OH"}) OHIO("OH","Ohio"),
    @JsonProperty("Oklahoma")               @JsonAlias({"OK"}) OKLAHOMA("OK","Oklahoma"),
    @JsonProperty("Oregon")                 @JsonAlias({"OR"}) OREGON("OR","Oregon"),
    @JsonProperty("Pennsylvania")           @JsonAlias({"PA"}) PENNSYLVANIA("PA","Pennsylvania"),
    @JsonProperty("Puerto Rico")            @JsonAlias({"PR"}) PUERTO_RICO("PR","Puerto Rico"),
    @JsonProperty("Rhode Island")           @JsonAlias({"RI"}) RHODE_ISLAND("RI","Rhode Island"),
    @JsonProperty("South Carolina")         @JsonAlias({"SC"}) SOUTH_CAROLINA("SC","South Carolina"),
    @JsonProperty("South Dakota")           @JsonAlias({"SD"}) SOUTH_DAKOTA("SD","South Dakota"),
    @JsonProperty("Tennessee")              @JsonAlias({"TN"}) TENNESSEE("TN","Tennessee"),
    @JsonProperty("Texas")                  @JsonAlias({"TX"}) TEXAS("TX","Texas"),
    @JsonProperty("Trust Territories")      @JsonAlias({"TT"}) TRUST_TERRITORIES("TT","Trust Territories"),
    @JsonProperty("Utah")                   @JsonAlias({"UT"}) UTAH("UT","Utah"),
    @JsonProperty("Vermont")                @JsonAlias({"VT"}) VERMONT("VT","Vermont"),
    @JsonProperty("Virginia")               @JsonAlias({"VA"}) VIRGINIA("VA","Virginia"),
    @JsonProperty("Virgin Islands")         @JsonAlias({"VI"}) VIRGIN_ISLANDS("VI","Virgin Islands"),
    @JsonProperty("Washington")             @JsonAlias({"WA"}) WASHINGTON("WA","Washington"),
    @JsonProperty("West Virginia")          @JsonAlias({"WV"}) WEST_VIRGINIA("WV","West Virginia"),
    @JsonProperty("Wisconsin")              @JsonAlias({"WI"}) WISCONSIN("WI","Wisconsin"),
    @JsonProperty("Wyoming")                @JsonAlias({"WY"}) WYOMING("WY","Wyoming")
            ;

    private final String abbreviation;
    private final String englishName;

    UnitedStatesState(String abbreviation, String englishName) {
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
