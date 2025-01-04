package it.didattica.cs.unicam.progettoloretimgc.model;

import it.didattica.cs.unicam.progettoloretimgc.model.Scooter;

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
