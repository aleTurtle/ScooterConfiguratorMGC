package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The Light class represents the lighting system of a scooter.
 * It stores information about the scooter and the description of the lighting system.
 */
public class Light {

    private Scooter scooter;
    private String description;

    /**
     * Constructor to initialize a Light object with the specified scooter and light description.
     *
     * @param scooter the scooter associated with the lighting system.
     * @param description the description of the lighting system
     */
    public Light(Scooter scooter, String description){
        this.scooter = scooter;
        this.description = description;
    }

    /**
     * Gets the description of the lighting system.
     *
     * @return the description of the lighting system associated with the scooter.
     */
    public String getLightDescription(){
        return description;
    }
}
