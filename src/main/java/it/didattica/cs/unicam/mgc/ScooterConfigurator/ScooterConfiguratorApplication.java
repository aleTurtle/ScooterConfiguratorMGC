package it.didattica.cs.unicam.mgc.ScooterConfigurator;

import it.didattica.cs.unicam.mgc.ScooterConfigurator.controller.ScooterConfiguratorController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The ScooterConfiguratorApplication class is the main entry point for the Scooter Configurator application.
 * It extends the JavaFX Application class and launches the GUI for configuring scooters.
 */
public class ScooterConfiguratorApplication extends Application {

    /**
     * The start method is called when the application is launched.
     * It initializes the ScooterConfiguratorController and launches the main scene for the application.
     *
     * @param stage the primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            //it creates an instance of the ScooterConfiguratorController
            ScooterConfiguratorController controller = new ScooterConfiguratorController();

            // it launches the application by passing the primary stage to the controller
            controller.launch(stage);
        } catch (Exception e) {
            // it prints any exception that occurs during the setup
            e.printStackTrace();
        }
    }

    /**
     * The main method is the entry point of the program and launches the JavaFX application.
     *
     * @param args command-line arguments (unused in this case).
     */
    public static void main(String[] args) {
        launch();
    }
}
