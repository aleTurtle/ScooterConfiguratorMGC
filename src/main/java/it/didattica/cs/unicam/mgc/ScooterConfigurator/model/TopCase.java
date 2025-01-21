package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

public class TopCase {
    private Scooter scooter;
    private String description;


    public TopCase(Scooter scooter, String description){
        this.scooter=scooter;
        this.description=description;
    }

    public String getTopCaseDescription(){
        return description;
    }
}
