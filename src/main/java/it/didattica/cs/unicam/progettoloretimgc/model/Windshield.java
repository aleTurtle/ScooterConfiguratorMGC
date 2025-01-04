package it.didattica.cs.unicam.progettoloretimgc.model;

import it.didattica.cs.unicam.progettoloretimgc.model.Scooter;

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
