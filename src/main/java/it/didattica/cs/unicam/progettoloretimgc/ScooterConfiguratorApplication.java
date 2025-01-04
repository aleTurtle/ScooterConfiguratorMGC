package it.didattica.cs.unicam.progettoloretimgc;

import it.didattica.cs.unicam.progettoloretimgc.controller.ScooterConfiguratorController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ScooterConfiguratorApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            ScooterConfiguratorController controller = new ScooterConfiguratorController();
            controller.launch(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
