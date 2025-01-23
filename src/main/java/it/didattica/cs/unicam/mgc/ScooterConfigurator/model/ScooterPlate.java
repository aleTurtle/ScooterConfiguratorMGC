package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The ScooterPlate class represents the plate (license plate) of a scooter.
 * It stores information about the scooter and its plate description.
 */
public class ScooterPlate {

    private Scooter scooter;
    private String description;

    /**
     * Constructor to initialize a ScooterPlate object with the specified scooter and plate description.
     *
     * @param scooter the scooter associated with the license plate.
     * @param description the description or identifier of the scooter's plate
     */
    public ScooterPlate(Scooter scooter, String description){
        this.scooter = scooter;
        this.description = description;
    }

    /**
     * Gets the identifier or description of the scooter's plate.
     *
     * @return the description or identifier of the scooter's plate
     */
    public String getScooterPlateIdentifier(){
        return description;
    }
}
