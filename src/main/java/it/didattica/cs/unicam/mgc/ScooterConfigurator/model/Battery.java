package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * Represents a battery component for a scooter.
 */

public class Battery {

    private Scooter scooter;
    private String batteryCapacity;

    /**
     * Constructor to create a Battery object with a specified scooter and a specified capacity.
     */
    public Battery(Scooter scooter, String batteryCapacity) {
        this.scooter = scooter;

        this.batteryCapacity = batteryCapacity;
    }

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
     * @param batteryCapacity The new capacity of the battery
     */
    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }


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
