package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The TopCase class represents the top case (storage compartment) of a scooter.
 * It stores information about the scooter and the description of its top case.
 */
public class TopCase {

    private Scooter scooter;
    private String description;

    /**
     * Constructor to initialize a TopCase object with the specified scooter and top case description.
     *
     * @param scooter the scooter associated with the top case.
     * @param description the description of the top case
     */
    public TopCase(Scooter scooter, String description){
        this.scooter = scooter;
        this.description = description;
    }

    /**
     * Gets the description of the scooter's top case.
     *
     * @return the description of the top case
     */
    public String getTopCaseDescription(){
        return description;
    }
}
