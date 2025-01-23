package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The Colour class represents the color associated with a scooter.
 * It stores information about the scooter and its associated color.
 */
public class Colour {

    private Scooter scooter;
    private String colourName;

    /**
     * Constructor to initialize a Colour object with the specified scooter and colour name.
     *
     * @param scooter the scooter associated with the color.
     * @param colourName the name of the color associated with the scooter.
     */
    public Colour(Scooter scooter, String colourName) {
        this.scooter = scooter;
        this.colourName = colourName;
    }

    /**
     * Gets the scooter associated with this color.
     *
     * @return the scooter associated with this color.
     */
    public Scooter getScooter() {
        return scooter;
    }

    /**
     * Sets the scooter associated with this color.
     *
     * @param scooter the scooter to associate with this color.
     */
    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    /**
     * Gets the name of the color.
     *
     * @return the name of the color associated with the scooter.
     */
    public String getColourName() {
        return colourName;
    }

    /**
     * Sets the name of the color.
     *
     * @param colourName the name of the color to set.
     */
    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    /**
     * Returns a string representation of the color.
     *
     * @return the name of the color.
     */
    @Override
    public String toString() {
        return colourName;
    }
}
