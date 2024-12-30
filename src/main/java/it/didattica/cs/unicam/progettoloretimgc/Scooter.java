package it.didattica.cs.unicam.progettoloretimgc;

import java.util.ArrayList;
import java.util.List;

public class Scooter {

    private String name;
    private String color;
    private List<String> accessories;
    private String battery;
    private String engine;

    public Scooter(String name) {
        this.name = name;
        this.accessories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
}
