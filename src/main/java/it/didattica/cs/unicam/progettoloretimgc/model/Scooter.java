package it.didattica.cs.unicam.progettoloretimgc.model;

import it.didattica.cs.unicam.progettoloretimgc.model.Colour;

import java.util.List;

public class Scooter {

    private String modelName;
    private Colour colour;
    private String color;
    private List<String> accessories;
    private String battery;
    private String engine;

    private String wheels;
    private String fuel;
    private String light;
    private String winshield;


    public Scooter(String modelName) {
        this.modelName = modelName;
        this.color = "Default Color"; // Colore di default
        this.engine = "Electric"; // Motore di default
        this.wheels = "Medium"; // Ruote di default
        this.battery = "Standard"; // Batteria di default
       // this.accessories = "None"; // Accessori di default
    }

    public String getName() {
        return modelName;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColor(String color) {
        this.color = color;  // Imposta il colore scelto
    }

    public void setFuel(String fuel) {this.fuel = fuel;}


    public List<String> getAccessories() {
        return accessories;
    }

    public void addAccessory(String accessory) {
        this.accessories.add(accessory);
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setLight(String light){
        this.light= light;
    }

    public void setWinshield(String winshield){
        this.winshield= winshield;
    }


    public void setWheels(String wheels) {
        this.wheels = wheels;
    }



}
