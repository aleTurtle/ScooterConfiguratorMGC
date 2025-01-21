package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

public class Brake {
    private Scooter scooter;

    private String description;


    public Brake(Scooter scooter, String description){
        this.scooter=scooter;
        this.description=description;
    }

    public String getBrakeDescription(){
        return description;
    }
}
