package it.didattica.cs.unicam.progettoloretimgc.model;

public class ScooterPlate {
    private Scooter scooter;

    private String description;


    public ScooterPlate(Scooter scooter, String description){
        this.scooter=scooter;
        this.description=description;
    }

    public String getScooterPlateIdentifier(){
        return description;
    }
}
