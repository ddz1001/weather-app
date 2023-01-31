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
    @JsonProperty("México")                 @JsonAlias({"MEX"}) MEXICO("MEX","México"),
    @JsonProperty("Michoacán")              @JsonAlias({"MIC"}) MICHOACAN("MIC","Michoacán"),
    @JsonProperty("Morelos")                @JsonAlias({"MOR"}) MORELOS("MOR","Morelos"),
    @JsonProperty("Nayarit")                @JsonAlias({"NAY"}) NAYARIT("NAY","Nayarit"),
    @JsonProperty("Nuevo León")             @JsonAlias({"NLE"}) NUEVO_LEON("NLE","Nuevo León"),
    @JsonProperty("Oaxaca")                 @JsonAlias({"OAX"}) OAXACA("OAX","Oaxaca"),
    @JsonProperty("Puebla")                 @JsonAlias({"PUE"}) PUEBLA("PUE","Puebla"),
    @JsonProperty("Querétaro")              @JsonAlias({"QUE"}) QUERETARO("QUE","Querétaro"),
    @JsonProperty("Quintana Roo")           @JsonAlias({"ROO"}) QUINTANA_ROO("ROO","Quintana Roo"),
    @JsonProperty("San Luis Potosí")        @JsonAlias({"SLP"}) SAN_LUIS_POTOSI("SLP","San Luis Potosí"),
    @JsonProperty("Sinaloa")                @JsonAlias({"SIN"}) SINALOA("SIN","Sinaloa"),
    @JsonProperty("Sonora")                 @JsonAlias({"SON"}) SONORA("SON","Sonora"),
    @JsonProperty("Tabasco")                @JsonAlias({"TAB"}) TABASCO("TAB","Tabasco"),
    @JsonProperty("Tamaulipas")             @JsonAlias({"TAM"}) TAMAULIPAS("TAM","Tamaulipas"),
    @JsonProperty("Tlaxcala")               @JsonAlias({"TLA"}) TLAXCALA("TLA","Tlaxcala"),
    @JsonProperty("Veracruz")               @JsonAlias({"VER"}) VERACRUZ("VER","Veracruz"),
    @JsonProperty("Yucatán")                @JsonAlias({"YUC"}) YUCATAN("YUC","Yucatán"),
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
