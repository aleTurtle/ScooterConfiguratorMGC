package it.didattica.cs.unicam.progettoloretimgc.model;

public class InternalCombustionEngine {
    private Scooter scooter;
    private String value;


    public InternalCombustionEngine(Scooter scooter, String value){
        this.scooter=scooter;
        this.value=value;
    }

    public String getICEvalue(){
        return value;
    }
}
