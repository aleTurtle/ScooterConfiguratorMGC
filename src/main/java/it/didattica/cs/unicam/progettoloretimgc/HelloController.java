package it.didattica.cs.unicam.progettoloretimgc;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class HelloController {
    @FXML
    private ComboBox<String> colorComboBox;
    @FXML
    private Label resultLabel;

    private Model ontologyModel;

    private Scooter scooter;

    private ListView<String> configurationList;

    @FXML
    private TabPane tabPane;
    @FXML
    private VBox sidebar;



    private void initializeTabs() {
        // Logica per configurare le Tab
        for (Tab tab : tabPane.getTabs()) {
            Button configureButton = new Button("Configure " + tab.getText());
            configureButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 8 16 8 16;");
            configureButton.setOnAction(e -> configureComponent(tab.getText()));

            VBox content = new VBox(10, configureButton);
            content.setPadding(new Insets(10));
            tab.setContent(content);
        }
    }

    private void initializeSidebar() {
        // Logica per la Sidebar
        Button finalizeButton = new Button("Finalize Configuration");
        finalizeButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        finalizeButton.setOnAction(e -> showFinalConfiguration());

        sidebar.getChildren().add(finalizeButton);
    }


    @FXML
    public void initialize() {
        // Carica l'ontologia RDF all'avvio
        loadOntology();


        // Configurazione iniziale
        initializeTabs();
        initializeSidebar();
        initializeColorComboBox();
        populateConfigurationList();
    }

    private void initializeColorComboBox() {
        // Popola il ComboBox con i colori disponibili
        colorComboBox.getItems().addAll("Rosso", "Blu", "Verde", "Giallo", "Nero");
    }

    private void addConfiguration(String configuration) {
        // Aggiunge una configurazione alla lista
        configurationList.getItems().add(configuration);
    }

    private void showFinalConfiguration() {
        // Mostra la configurazione finale
        String summary = String.join("\n", configurationList.getItems());
        showAlert(Alert.AlertType.INFORMATION, "Configurazione Finale", summary);
    }

    private void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "Errore", message);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void configureComponent(String component) {
        // Logica per configurare ciascun componente
        String message = "Configurazione completata per: " + component;
        addConfiguration(message);
        showAlert(Alert.AlertType.INFORMATION, "Configurazione", message);


        switch (component) {
            case "Colour":
                configureColour();
                break;
            default:
                showError("Invalid component selected.");
        }
    }

    private void configureColour() {
        List<Colour> colours = queryService.getColourComponents(scooter);
        if (colours.isEmpty()) {
            showError("No colours found.");
            return;
        }
        ChoiceDialog<Colour> colourDialog = new ChoiceDialog<>(colours.get(0), colours);
        colourDialog.setTitle("Colour Configuration");
        colourDialog.setHeaderText("Select a colour");
        colourDialog.setContentText("Colour:");

        // Load the custom icon image
        ImageView customIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/colour.png")));
        customIcon.setFitHeight(50);
        customIcon.setFitWidth(50);

        // Set the custom icon as the graphic for the dialog's header
        colourDialog.setGraphic(customIcon);

        colourDialog.showAndWait().ifPresent(selectedColour -> {
            scooter.setColour(selectedColour);
            updateConfigurationList("Colour", selectedColour.getColourName());
        });
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
