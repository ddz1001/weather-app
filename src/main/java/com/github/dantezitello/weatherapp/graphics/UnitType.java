package com.github.dantezitello.weatherapp.graphics;

public enum UnitType {

    CELCIUS {
        @Override
        public String getSymbol() {
            return "°C";
        }
    },
    FAHRENHEIT {
        @Override
        public String getSymbol() {
            return "°F";
        }
    },
    KELVIN {
        @Override
        public String getSymbol() {
            return "°K";
        }
    };


    public abstract String getSymbol();

}
