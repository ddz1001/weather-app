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

public enum MexicanState implements AdministrationRegion{
    //This file was generated using excel and data from the ISO website


    @JsonProperty("Mexico City")            @JsonAlias({"CMX"}) MEXICO_CITY("CMX","Mexico City"),
    @JsonProperty("Aguascalientes")         @JsonAlias({"AGU"}) AGUASCALIENTES("AGU","Aguascalientes"),
    @JsonProperty("Baja California")        @JsonAlias({"BCN"}) BAJA_CALIFORNIA("BCN","Baja California"),
    @JsonProperty("Baja California Sur")    @JsonAlias({"BCS"}) BAJA_CALIFORNIA_SUR("BCS","Baja California Sur"),
    @JsonProperty("Campeche")               @JsonAlias({"CAM"}) CAMPECHE("CAM","Campeche"),
    @JsonProperty("Coahuila")               @JsonAlias({"COA"}) COAHUILA("COA","Coahuila"),
    @JsonProperty("Colima")                 @JsonAlias({"COL"}) COLIMA("COL","Colima"),
    @JsonProperty("Chiapas")                @JsonAlias({"CHP"}) CHIAPAS("CHP","Chiapas"),
    @JsonProperty("Chihuahua")              @JsonAlias({"CHH"}) CHIHUAHUA("CHH","Chihuahua"),
    @JsonProperty("Durango")                @JsonAlias({"DUR"}) DURANGO("DUR","Durango"),
    @JsonProperty("Guanajuato")             @JsonAlias({"GUA"}) GUANAJUATO("GUA","Guanajuato"),
    @JsonProperty("Guerrero")               @JsonAlias({"GRO"}) GUERRERO("GRO","Guerrero"),
    @JsonProperty("Hidalgo")                @JsonAlias({"HID"}) HIDALGO("HID","Hidalgo"),
    @JsonProperty("Jalisco")                @JsonAlias({"JAL"}) JALISCO("JAL","Jalisco"),
    @JsonProperty("M??xico")                 @JsonAlias({"MEX"}) MEXICO("MEX","M??xico"),
    @JsonProperty("Michoac??n")              @JsonAlias({"MIC"}) MICHOACAN("MIC","Michoac??n"),
    @JsonProperty("Morelos")                @JsonAlias({"MOR"}) MORELOS("MOR","Morelos"),
    @JsonProperty("Nayarit")                @JsonAlias({"NAY"}) NAYARIT("NAY","Nayarit"),
    @JsonProperty("Nuevo Le??n")             @JsonAlias({"NLE"}) NUEVO_LEON("NLE","Nuevo Le??n"),
    @JsonProperty("Oaxaca")                 @JsonAlias({"OAX"}) OAXACA("OAX","Oaxaca"),
    @JsonProperty("Puebla")                 @JsonAlias({"PUE"}) PUEBLA("PUE","Puebla"),
    @JsonProperty("Quer??taro")              @JsonAlias({"QUE"}) QUERETARO("QUE","Quer??taro"),
    @JsonProperty("Quintana Roo")           @JsonAlias({"ROO"}) QUINTANA_ROO("ROO","Quintana Roo"),
    @JsonProperty("San Luis Potos??")        @JsonAlias({"SLP"}) SAN_LUIS_POTOSI("SLP","San Luis Potos??"),
    @JsonProperty("Sinaloa")                @JsonAlias({"SIN"}) SINALOA("SIN","Sinaloa"),
    @JsonProperty("Sonora")                 @JsonAlias({"SON"}) SONORA("SON","Sonora"),
    @JsonProperty("Tabasco")                @JsonAlias({"TAB"}) TABASCO("TAB","Tabasco"),
    @JsonProperty("Tamaulipas")             @JsonAlias({"TAM"}) TAMAULIPAS("TAM","Tamaulipas"),
    @JsonProperty("Tlaxcala")               @JsonAlias({"TLA"}) TLAXCALA("TLA","Tlaxcala"),
    @JsonProperty("Veracruz")               @JsonAlias({"VER"}) VERACRUZ("VER","Veracruz"),
    @JsonProperty("Yucat??n")                @JsonAlias({"YUC"}) YUCATAN("YUC","Yucat??n"),
    @JsonProperty("Zacatecas")              @JsonAlias({"ZAC"}) ZACATECAS("ZAC","Zacatecas");




    private final String abbreviation;
    private final String englishName;

    MexicanState(String abbreviation, String englishName) {
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
