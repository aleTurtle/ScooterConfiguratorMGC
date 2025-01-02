package it.didattica.cs.unicam.progettoloretimgc;

public class Colour {

    private Scooter scooter;
    private String colourName;

    public Colour(Scooter scooter, String colourName) {
        this.scooter = scooter;
        this.colourName = colourName;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    @Override
    public String toString() {
        return colourName;
    }

}
