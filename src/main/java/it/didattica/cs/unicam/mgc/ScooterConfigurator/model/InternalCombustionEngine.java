package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The InternalCombustionEngine class represents the internal combustion engine of a scooter.
 * It stores information about the scooter and the value (power rating) of the internal combustion engine.
 */
public class InternalCombustionEngine {

    private Scooter scooter;
    private String value;

    /**
     * Constructor to initialize an InternalCombustionEngine object with the specified scooter and engine power value.
     *
     * @param scooter the scooter associated with the internal combustion engine.
     * @param value the power value of the internal combustion engine
     */
    public InternalCombustionEngine(Scooter scooter, String value){
        this.scooter = scooter;
        this.value = value;
    }

    /**
     * Gets the power value of the internal combustion engine.
     *
     * @return the power value of the internal combustion engine
     */
    public String getICEvalue(){
        return value;
    }
}
