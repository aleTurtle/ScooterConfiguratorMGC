package it.didattica.cs.unicam.progettoloretimgc;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class HelloController {
    @FXML
    private ComboBox<String> colorComboBox;
    @FXML
    private Label resultLabel;

    private Model ontologyModel;

    private Scooter scooter;

    private ListView<String> configurationList;




    @FXML
    public void initialize() {
        // Carica l'ontologia RDF all'avvio
        loadOntology();


        // Popola il ComboBox con i colori disponibili
        colorComboBox.getItems().addAll("Rosso", "Blu", "Verde", "Giallo", "Nero");

        // Popola la sidebar con configurazioni iniziali
        populateConfigurationList();
    }

    @FXML
    protected void onConfigureButtonClick() {
        String selectedColor = colorComboBox.getValue();
        if (selectedColor == null || selectedColor.isEmpty()) {
            resultLabel.setText("Seleziona un colore.");
            return;
        }

        // Esegui la query per configurare il colore
        boolean success = configureColor(selectedColor);
        if (success) {
            resultLabel.setText("Colore configurato con successo: " + selectedColor);
            // Aggiorna la lista delle configurazioni
            updateConfigurationList("Colore configurato: " + selectedColor);
        } else {
            resultLabel.setText("Errore durante la configurazione.");
        }
    }

    @FXML
    protected void onSidebarOption1Click() {
        resultLabel.setText("Hai cliccato su Opzione 1.");
        updateConfigurationList("Opzione 1 selezionata.");
    }

    @FXML
    protected void onSidebarOption2Click() {
        resultLabel.setText("Hai cliccato su Opzione 2.");
        updateConfigurationList("Opzione 2 selezionata.");
    }


    private void loadOntology() {
        try {
            // Ottieni il file RDF dal classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("it/didattica/cs/unicam/progettoloretimgc/ontology.rdf");

            if (inputStream == null) {
                throw new FileNotFoundException("L'ontologia non è stata trovata nel classpath.");
            }

            ontologyModel = ModelFactory.createDefaultModel();
            ontologyModel.read(inputStream, null); // Leggi il file RDF dal classpath

            // Aggiungi un messaggio che indica che l'ontologia è stata caricata
            resultLabel.setText("Ontologia caricata con successo.");





        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Errore nel caricamento dell'ontologia.");
        }
    }

    private void populateConfigurationList() {
        if (configurationList != null) {
            configurationList.getItems().clear();
            configurationList.getItems().addAll(
                    "Configurazione iniziale: Default",
                    "Colore: Non configurato"
            );
        }
    }

    private void updateConfigurationList(String newConfiguration) {
        if (configurationList != null) {
            configurationList.getItems().add(newConfiguration);
        }
    }

    private boolean configureColor(String color) {
        try {
            String updateQuery = String.format(
                    "PREFIX ex: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#> " +
                            "DELETE { ?scooter ex:color ?oldColor } " +
                            "INSERT { ?scooter ex:color '%s' } " +
                            "WHERE { ?scooter ex:color ?oldColor }",
                    color
            );

            UpdateRequest updateRequest = UpdateFactory.create(updateQuery);
            UpdateProcessor processor = UpdateExecutionFactory.create(updateRequest, DatasetFactory.create(ontologyModel));
            processor.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
