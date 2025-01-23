package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The Wheel class represents a wheel of a scooter.
 * It stores information about the scooter and the description of its wheel.
 */
public class Wheel {

    private Scooter scooter;
    private String description;

    /**
     * Constructor to initialize a Wheel object with the specified scooter and wheel description.
     *
     * @param scooter the scooter associated with the wheel.
     * @param description the description of the wheel
     */
    public Wheel(Scooter scooter, String description){
        this.scooter = scooter;
        this.description = description;
    }

    /**
     * Gets the description of the scooter's wheel.
     *
     * @return the description of the wheel
     */
    public String getWheelDescription(){
        return description;
    }
}
