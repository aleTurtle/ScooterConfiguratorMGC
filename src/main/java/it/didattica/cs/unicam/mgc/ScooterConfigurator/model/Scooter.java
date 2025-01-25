package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

import java.util.List;

public class Scooter {
    // this class represents the most important element for the final configuration
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
        this.color = "Default Color"; // Default color
        this.engine = "Electric"; // Default engine
        this.wheels = "Medium"; // Default wheels
        this.battery = "Standard"; // Default battery
        // this.accessories = "None"; // Default accessories
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
