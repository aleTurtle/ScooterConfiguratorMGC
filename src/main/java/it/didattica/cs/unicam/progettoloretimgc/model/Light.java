package it.didattica.cs.unicam.progettoloretimgc.model;

public class Light {
    private Scooter scooter;

    private String description;


    public Light(Scooter scooter, String description){
        this.scooter=scooter;
        this.description=description;
    }

    public String getLightDescription(){
        return description;
    }


}
