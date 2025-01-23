package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The Brake class represents a brake system associated with a scooter.
 * It holds information about the scooter and the description of the brake system.
 */
public class Brake {

    private Scooter scooter;
    private String description;

    /**
     * Constructor to initialize a Brake object with the specified scooter and brake description.
     *
     * @param scooter the scooter associated with the brake system.
     * @param description a description of the brake system.
     */
    public Brake(Scooter scooter, String description){
        this.scooter = scooter;
        this.description = description;
    }

    /**
     * Gets the description of the brake system.
     *
     * @return the description of the brake system.
     */
    public String getBrakeDescription(){
        return description;
    }
}
