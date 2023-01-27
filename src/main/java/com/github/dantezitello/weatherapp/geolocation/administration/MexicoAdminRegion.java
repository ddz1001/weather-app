package com.github.dantezitello.weatherapp.geolocation.administration;

import java.util.HashMap;
import java.util.Map;

public enum MexicoAdminRegion {

    AG("Aguascalientes"),
    BC("Baja California"),
    BS("Baja California Sur"),
    CM("Campeche"),
    CS("Chiapas"),
    CH("Chihuahua"),
    CO("Coahuila"),
    CL("Colima"),
    DF("Mexico City"),
    DG("Durango"),
    GT("Guanajuato"),
    GR("Guerrero"),
    HG("Hidalgo"),
    JA("Jalisco"),
    EM("México"),
    MI("Michoacán"),
    MO("Morelos"),
    NA("Nayarit"),
    NL("Nuevo León"),
    OA("Oaxaca"),
    PU("Puebla"),
    QT("Querétaro"),
    QR("Quintana Roo"),
    SL("San Luis Potosí"),
    SI("Sinaloa"),
    SO("Sonora"),
    TB("Tabasco"),
    TM("Tamaulipas"),
    TL("Tlaxcala"),
    VE("Veracruz"),
    YU("Yucatán"),
    ZA("Zacatecas");


    private final String fullname;

    MexicoAdminRegion(String fullname) {
        this.fullname = fullname;
    }

    public String getFullName() {
        return fullname;
    }

    private static final Map<String, MexicoAdminRegion> byAbbrev = new HashMap<>(25);
    private static final Map<String, MexicoAdminRegion> byName = new HashMap<>(25);



    public static MexicoAdminRegion findByAbbreviation(String abbreviation) {
        if(byAbbrev.isEmpty()) {
            //init
            for(MexicoAdminRegion code : MexicoAdminRegion.values()) {
                byAbbrev.put( code.name().toUpperCase(), code );
            }
        }

        return byAbbrev.get(abbreviation.toUpperCase());
    }

    public static MexicoAdminRegion findByFullName(String name) {
        if(byName.isEmpty()) {
            //init
            for(MexicoAdminRegion code : MexicoAdminRegion.values()) {
                byName.put( code.getFullName().toUpperCase(), code );
            }
        }

        return byName.get(name.toUpperCase());
    }

}
