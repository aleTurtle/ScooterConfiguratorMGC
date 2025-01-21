package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

public class Seat {
    private Scooter scooter;

    private String description;


    public Seat(Scooter scooter, String description){
        this.scooter=scooter;
        this.description=description;
    }

    public String getSeatDescription(){
        return description;
    }
}
