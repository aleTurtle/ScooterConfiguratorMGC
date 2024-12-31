package it.didattica.cs.unicam.progettoloretimgc;

public class Colour {

    private String colourName;

    private Scooter colourOfLaptop;

    /**
     * Constructs a Colour with the specified laptop and name
     * @param colourOfLaptop the laptop to which the colour is associated
     * @param colourName the name of the colour
     */

    public Colour(Scooter colourOfLaptop, String colourName) {
        this.colourOfLaptop = colourOfLaptop;
        this.colourName = colourName;
    }

    /**
     * Clones the Colour object
     * @param scooter the scooter to which the Colour object is associated
     * @return a new Colour object
     */

    public Colour clone(Scooter scooter) {
        return new Colour(scooter, this.colourName);
    }

    /**
     * @return the laptop to which the colour is associated
     */

    public Scooter getColourOfLaptop() {
        return colourOfLaptop;
    }

    /**
     * sets the laptop to which the colour is associated
     * @param colourOfLaptop the laptop to which the colour is associated
     */

    public void setColourOfLaptop(Scooter colourOfLaptop) {
        this.colourOfLaptop = colourOfLaptop;
    }

    /**
     * @return the name of the colour
     */

    public String getColourName() {
        return colourName;
    }

    /**
     * sets the name of the colour
     * @param HasColourName the name of the colour
     */

    public void setColourName(String HasColourName) {
        this.colourName = HasColourName;
    }

    /**
     * @return the name of the colour
     */
    @Override
    public String toString() {
        return colourName;
    }


}
