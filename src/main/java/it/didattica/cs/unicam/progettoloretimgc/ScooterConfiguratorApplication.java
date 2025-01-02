package it.didattica.cs.unicam.progettoloretimgc;

import it.didattica.cs.unicam.progettoloretimgc.ontology.OntologyLoader;
import it.didattica.cs.unicam.progettoloretimgc.ontology.QueryService;
import it.didattica.cs.unicam.progettoloretimgc.ontology.SPARQLQueryExecutor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.jena.ontology.OntModel;

import java.io.IOException;
import java.net.URL;

public class ScooterConfiguratorApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Carica l'ontologia e inizializza QueryService
            String ontologyFilePath = "ScooterOntology.rdf";
            OntologyLoader ontologyLoader = new OntologyLoader(ontologyFilePath);
            OntModel model = ontologyLoader.getOntologyModel();
            if (model == null) {
                throw new IOException("Errore nel caricamento dell'ontologia: Modello non trovato o non valido.");
            }
            QueryService queryService = new QueryService(new SPARQLQueryExecutor(model));

            // Carica il file FXML
            URL fxmlResource = getClass().getResource("/ScooterConfigurator-view.fxml");
            if (fxmlResource == null) {
                throw new IOException("File FXML non trovato: ScooterConfigurator-view.fxml");
            }

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource);


            // Imposta il controller manualmente
            ScooterConfiguratorController controller = new ScooterConfiguratorController();
            controller.setQueryService(queryService); // Passa il QueryService al controller
            fxmlLoader.setController(controller);

            // Crea la scena
            Scene scene = new Scene(fxmlLoader.load(), 720, 540);
            stage.setTitle("Scooter configurator!");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Errore di IO durante l'avvio dell'applicazione: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Errore generico durante l'avvio dell'applicazione: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
