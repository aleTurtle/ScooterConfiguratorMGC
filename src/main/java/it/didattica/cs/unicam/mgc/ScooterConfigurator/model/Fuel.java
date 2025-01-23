package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The Fuel class represents the fuel system associated with a scooter.
 * It holds information about the scooter and the name of the fuel used by the scooter.
 */
public class Fuel {

    private Scooter scooter;
    private String fuelName;

    /**
     * Constructor to initialize a Fuel object with the specified scooter and fuel name.
     *
     * @param scooter the scooter associated with the fuel system.
     * @param fuelName the name of the fuel used by the scooter
     */
    public Fuel(Scooter scooter, String fuelName) {
        this.scooter = scooter;
        this.fuelName = fuelName;
    }

    /**
     * Gets the name of the fuel used by the scooter.
     *
     * @return the name of the fuel
     */
    public String getFuelName() {
        return fuelName;
    }

}
