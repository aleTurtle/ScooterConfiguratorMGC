package it.didattica.cs.unicam.mgc.ScooterConfigurator.controller;

import it.didattica.cs.unicam.mgc.ScooterConfigurator.ontology.SPARQLQueryExecutor;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.ontology.OntologyLoader;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.ontology.QueryService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.jena.ontology.OntModel;

import java.io.IOException;
import java.net.URL;

/**
 * The main controller for the Scooter Configurator application.
 * This class manages the initialization and launching of the application,
 * loading the ontology model, and setting up the scene for the user interface.
 */
public class ScooterConfiguratorController {

    private final QueryService queryService;

    /**
     * Constructor to initialize the QueryService.
     * It loads the ontology file and sets up the query service for querying the ontology.
     */
    public ScooterConfiguratorController() throws IOException {
        String ontologyFilePath = "ScooterOntology.rdf";
        OntologyLoader ontologyLoader = new OntologyLoader(ontologyFilePath);
        OntModel model = ontologyLoader.getOntologyModel();

        if (model == null) {
            throw new IOException("Error loading the ontology.");
        }

        this.queryService = new QueryService(new SPARQLQueryExecutor(model));
    }

    /**
     * Method to launch the application.
     * It loads the FXML file, initializes the scene, and sets up the main stage.
     */
    public void launch(Stage stage) throws IOException {
        // Load the FXML file
        URL fxmlResource = getClass().getResource("/ScooterConfigurator-view.fxml");
        if (fxmlResource == null) {
            throw new IOException("FXML file not found.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource);

        // Load the scene from the FXML file
        Scene scene = new Scene(fxmlLoader.load(), 870, 600);

        // Retrieve the controller from the FXML file
        ScooterViewController viewController = fxmlLoader.getController();

        if (viewController == null) {
            throw new IllegalStateException("The controller was not loaded correctly from the FXML file.");
        }

        // Pass the QueryService to the FXML controller
        viewController.setQueryService(this.queryService);

        // Set up and show the window
        stage.setTitle("Scooter Configurator");
        stage.setScene(scene);
        stage.show();
    }
}
