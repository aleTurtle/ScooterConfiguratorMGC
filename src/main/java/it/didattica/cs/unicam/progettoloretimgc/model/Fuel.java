package it.didattica.cs.unicam.progettoloretimgc.model;

public class Fuel {
    private Scooter scooter;
    private String fuelName;

    public Fuel(Scooter scooter, String fuelName) {
        this.scooter = scooter;
        this.fuelName = fuelName;
    }

    public String getFuelName() {
        return fuelName;
    }

}
