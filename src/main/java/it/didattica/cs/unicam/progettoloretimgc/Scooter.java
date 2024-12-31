package it.didattica.cs.unicam.progettoloretimgc;

import java.util.ArrayList;
import java.util.List;

public class Scooter {

    private String modelName;
    private Colour colour;
    private String color;
    private List<String> accessories;
    private String battery;
    private String engine;

    private String wheels;


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

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }

}
