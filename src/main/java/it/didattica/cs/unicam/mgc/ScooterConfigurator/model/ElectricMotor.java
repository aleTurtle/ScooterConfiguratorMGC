package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The ElectricMotor class represents the electric motor of a scooter.
 * It stores information about the scooter and the value (power rating) of the electric motor.
 */
public class ElectricMotor {

    private Scooter scooter;
    private String value;

    /**
     * Constructor to initialize an ElectricMotor object with the specified scooter and motor power value.
     *
     * @param scooter the scooter associated with the electric motor.
     * @param value the power value of the electric motor
     */
    public ElectricMotor(Scooter scooter, String value){
        this.scooter = scooter;
        this.value = value;
    }

    /**
     * Gets the power value of the electric motor.
     *
     * @return the power value of the electric motor
     */
    public String getElectricPowerValue(){
        return value;
    }
}
