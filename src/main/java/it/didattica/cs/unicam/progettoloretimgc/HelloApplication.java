package it.didattica.cs.unicam.progettoloretimgc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Percorso assoluto al file FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/it/didattica/cs/unicam/progettoloretimgc/hello-view.fxml"));

        // Crea la scena
        Scene scene = new Scene(fxmlLoader.load(), 720, 540);
        stage.setTitle("Scooter configurator!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
