package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The Windshield class represents the windshield of a scooter.
 * It stores information about the scooter and the description of its windshield.
 */
public class Windshield {

    private Scooter scooter;
    private String description;

    /**
     * Constructor to initialize a Windshield object with the specified scooter and windshield description.
     *
     * @param scooter the scooter associated with the windshield.
     * @param description the description of the windshield
     */
    public Windshield(Scooter scooter, String description){
        this.scooter = scooter;
        this.description = description;
    }

    /**
     * Gets the description of the scooter's windshield.
     *
     * @return the description of the windshield
     */
    public String getWindshieldDescription(){
        return description;
    }
}
