package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

public class Wheel {
    private Scooter scooter;

    private String description;


    public Wheel(Scooter scooter, String description){
        this.scooter=scooter;
        this.description=description;
    }

    public String getWheelDescription(){
        return description;
    }
}
