package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

/**
 * The Seat class represents the seat of a scooter.
 * It stores information about the scooter and the description of its seat.
 */
public class Seat {

    private Scooter scooter;
    private String description;

    /**
     * Constructor to initialize a Seat object with the specified scooter and seat description.
     *
     * @param scooter the scooter associated with the seat.
     * @param description the description of the seat
     */
    public Seat(Scooter scooter, String description){
        this.scooter = scooter;
        this.description = description;
    }

    /**
     * Gets the description of the scooter's seat.
     *
     * @return the description of the seat
     */
    public String getSeatDescription(){
        return description;
    }
}
