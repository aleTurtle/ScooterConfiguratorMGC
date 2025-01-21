package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

public class Windshield {

        private Scooter scooter;
        private String description;


    public Windshield(Scooter scooter, String description){
            this.scooter=scooter;
            this.description=description;
        }

        public String getWindshieldDescription(){
            return description;
        }
}
