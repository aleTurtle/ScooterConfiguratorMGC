package it.didattica.cs.unicam.progettoloretimgc.controller;

import it.didattica.cs.unicam.progettoloretimgc.ontology.OntologyLoader;
import it.didattica.cs.unicam.progettoloretimgc.ontology.QueryService;
import it.didattica.cs.unicam.progettoloretimgc.ontology.SPARQLQueryExecutor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.jena.ontology.OntModel;

import java.io.IOException;
import java.net.URL;

public class ScooterConfiguratorController {

    private final QueryService queryService;

    // Costruttore per inizializzare il QueryService
    public ScooterConfiguratorController() throws IOException {
        String ontologyFilePath = "ScooterOntology.rdf";
        OntologyLoader ontologyLoader = new OntologyLoader(ontologyFilePath);
        OntModel model = ontologyLoader.getOntologyModel();
        if (model == null) {
            throw new IOException("Errore nel caricamento dell'ontologia.");
        }
        this.queryService = new QueryService(new SPARQLQueryExecutor(model));
    }

    // Metodo per avviare l'applicazione
    public void launch(Stage stage) throws IOException {
        // Carica il file FXML
        URL fxmlResource = getClass().getResource("/ScooterConfigurator-view.fxml");
        if (fxmlResource == null) {
            throw new IOException("File FXML non trovato.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource);

        // Carica la scena dal file FXML
        Scene scene = new Scene(fxmlLoader.load(), 810, 600);

        // Recupera il controller dal FXML
        ScooterViewController viewController = fxmlLoader.getController();

        if (viewController == null) {
            throw new IllegalStateException("Il controller non è stato caricato correttamente dal file FXML.");
        }

        // Passa il QueryService al controller del file FXML
        viewController.setQueryService(this.queryService);

        // Configura e mostra la finestra
        stage.setTitle("Scooter Configurator");
        stage.setScene(scene);
        stage.show();
    }
}
