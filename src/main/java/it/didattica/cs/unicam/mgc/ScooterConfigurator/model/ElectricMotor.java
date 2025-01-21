package it.didattica.cs.unicam.mgc.ScooterConfigurator.model;

public class ElectricMotor {
    private Scooter scooter;
    private String value;


    public ElectricMotor(Scooter scooter, String value){
        this.scooter=scooter;
        this.value=value;
    }

    public String getElectricPowerValue(){
        return value;
    }
}
