package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * Represents a battery component for a scooter.
 */

public class Battery {

    private Scooter scooter;
    private String batteryCapacity;

    /**
     * Constructor to create a Battery object with a specified scooter, name, and capacity.
     *
     * @param scooter The scooter to associate with the battery.

     * @param batteryCapacity The capacity of the battery (mAh).
     */
    public Battery(Scooter scooter, String batteryCapacity) {
        this.scooter = scooter;

        this.batteryCapacity = batteryCapacity;
    }

    /**
     * Clones the battery and associate with scooter.
     *
     * @param scooter The laptop to which the battery will be associated.
     * @return A new Battery object with the same name and capacity.
     */

    /**
     * Gets the battery capacity.
     *
     * @return The capacity of the battery in mAh.
     */
    public String getBatteryCapacity() {

       // return batteryCapacity.toString();
        return batteryCapacity;
    }

    /**
     * Sets the battery capacity.
     *
     * @param batteryCapacity The new capacity of the battery in mAh.
     */
    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * Get the battery associated with the scooter.
     *
     * @return The scooter associated with the battery.
     */
    public Scooter getBatteryOfLaptop() {
        return scooter;
    }

    /**
     * Sets the laptop associated with the battery.
     *
     * @param scooter The new scooter to associate with the battery.
     */
    public void setBatteryOfScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    /**
     * Gets the battery name.
     *
     * @return The name of the battery.
     */


    /**
     * Sets the battery name.
     *
     * @param batteryName The new name of the battery.
     */


    /**
     * Returns a string representation of the battery, including its name and capacity.
     *
     * @return A string describing the battery.
     */
    @Override
    public String toString() {
        return  " Battery (" + batteryCapacity + " KWh)";
    }
}
