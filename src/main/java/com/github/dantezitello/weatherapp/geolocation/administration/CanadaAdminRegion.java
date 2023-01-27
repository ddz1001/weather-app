package com.github.dantezitello.weatherapp.geolocation.administration;

import java.util.HashMap;
import java.util.Map;

public enum CanadaAdminRegion {

    NL("Newfoundland and Labrador"),
    PE("Prince Edward Island"),
    NS("Nova Scotia"),
    NB("New Brunswick"),
    QC("Quebec"),
    ON("Ontario"),
    MB("Manitoba"),
    SK("Saskatchewan"),
    AB("Alberta"),
    BC("British Columbia"),
    YT("Yukon"),
    NT("Northwest Territories"),
    NU("Nunavut");

    private final String fullname;

    CanadaAdminRegion(String fullname) {
        this.fullname = fullname;
    }

    public String getFullName() {
        return fullname;
    }

    private static final Map<String, CanadaAdminRegion> byAbbrev = new HashMap<>(25);
    private static final Map<String, CanadaAdminRegion> byName = new HashMap<>(25);



    public static CanadaAdminRegion findByAbbreviation(String abbreviation) {
        if(byAbbrev.isEmpty()) {
            //init
            for(CanadaAdminRegion code : CanadaAdminRegion.values()) {
                byAbbrev.put( code.name().toUpperCase(), code );
            }
        }

        return byAbbrev.get(abbreviation.toUpperCase());
    }

    public static CanadaAdminRegion findByFullName(String name) {
        if(byName.isEmpty()) {
            //init
            for(CanadaAdminRegion code : CanadaAdminRegion.values()) {
                byName.put( code.getFullName().toUpperCase(), code );
            }
        }

        return byName.get(name.toUpperCase());
    }

}
