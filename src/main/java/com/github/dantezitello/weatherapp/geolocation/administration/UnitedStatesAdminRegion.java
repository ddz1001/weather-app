package com.github.dantezitello.weatherapp.geolocation.administration;

import java.util.HashMap;
import java.util.Map;

public enum UnitedStatesAdminRegion {
    AL("Alabama"),
    AK("Alaska"),
    AZ("Arizona"),
    AR("Arkansas"),
    AS("American Samoa"),
    CA("California"),
    CO("Colorado"),
    CT("Connecticut"),
    DE("Delaware"),
    DC("District of Columbia"),
    FL("Florida"),
    GA("Georgia"),
    GU("Guam"),
    HI("Hawaii"),
    ID("Idaho"),
    IL("Illinois"),
    IN("Indiana"),
    IA("Iowa"),
    KS("Kansas"),
    KY("Kentucky"),
    LA("Louisiana"),
    ME("Maine"),
    MD("Maryland"),
    MA("Massachusetts"),
    MI("Michigan"),
    MN("Minnesota"),
    MS("Mississippi"),
    MO("Missouri"),
    MT("Montana"),
    NE("Nebraska"),
    NV("Nevada"),
    NH("New Hampshire"),
    NJ("New Jersey"),
    NM("New Mexico"),
    NY("New York"),
    NC("North Carolina"),
    ND("North Dakota"),
    MP("Northern Mariana Islands"),
    OH("Ohio"),
    OK("Oklahoma"),
    OR("Oregon"),
    PA("Pennsylvania"),
    PR("Puerto Rico"),
    RI("Rhode Island"),
    SC("South Carolina"),
    SD("South Dakota"),
    TN("Tennessee"),
    TX("Texas"),
    TT("Trust Territories"),
    UT("Utah"),
    VT("Vermont"),
    VA("Virginia"),
    VI("Virgin Islands"),
    WA("Washington"),
    WV("West Virginia"),
    WI("Wisconsin"),
    WY("Wyoming");


    private final String fullName;
    private static final Map<String, UnitedStatesAdminRegion> byAbbrev = new HashMap<>(60);
    private static final Map<String, UnitedStatesAdminRegion> byName = new HashMap<>(60);

    UnitedStatesAdminRegion(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public static UnitedStatesAdminRegion findByAbbreviation(String abbreviation) {
        if(byAbbrev.isEmpty()) {
            //init
            for(UnitedStatesAdminRegion code : UnitedStatesAdminRegion.values()) {
                byAbbrev.put( code.name().toUpperCase(), code );
            }
        }

        return byAbbrev.get(abbreviation.toUpperCase());
    }

    public static UnitedStatesAdminRegion findByFullName(String name) {
        if(byName.isEmpty()) {
            //init
            for(UnitedStatesAdminRegion code : UnitedStatesAdminRegion.values()) {
                byName.put( code.getFullName().toUpperCase(), code );
            }
        }

        return byName.get(name.toUpperCase());
    }




}

