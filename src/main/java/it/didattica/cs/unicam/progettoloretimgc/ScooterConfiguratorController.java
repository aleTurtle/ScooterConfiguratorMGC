package it.didattica.cs.unicam.progettoloretimgc;

import it.didattica.cs.unicam.progettoloretimgc.ontology.OntologyLoader;
import it.didattica.cs.unicam.progettoloretimgc.ontology.QueryService;
import it.didattica.cs.unicam.progettoloretimgc.ontology.SPARQLQueryExecutor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.jena.ontology.OntModel;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ScooterConfiguratorController {

    @FXML
    private ListView<String> configurationList;

    @FXML
    private ComboBox<String> colorComboBox; // ComboBox per la selezione del colore
    private QueryService queryService;

    @FXML
    private Tab colorTab, engineTab, wheelsTab, batteryTab, accessoriesTab;

    // Simulazione di un oggetto Scooter e delle sue configurazioni
    private Scooter scooter= new Scooter("MyScooter");


    // Costruttore per iniettare il QueryService
    public ScooterConfiguratorController() {
    }

    // Metodo per impostare QueryService
    public void setQueryService(QueryService queryService) {
        this.queryService = queryService;
    }


    // Metodo per aggiungere un componente alla lista di configurazione
    private void updateConfigurationList(String componentName, String componentValue) {
        configurationList.getItems().add(componentName + ": " + componentValue);
    }




    // Metodo per configurare il colore
    @FXML
    private void initialize() {

        // Impostare la lista dei colori nel ComboBox
        // Recupera la lista dei colori tramite il queryService
        List<Colour> colorComponents = queryService.getColourComponents(scooter);

        // Estrai i nomi dei colori dalla lista di oggetti Colour
        List<String> colorNames = colorComponents.stream()
                .map(Colour::getColourName)  // Recupera il nome del colore
                .collect(Collectors.toList());

        // Controlla se il ComboBox è già popolato
        if (colorComboBox.getItems().isEmpty()) {
            colorComboBox.setItems(FXCollections.observableArrayList(colorNames));
        }
    }

    // Metodo per configurare il colore
    @FXML
    private void configureColor() {
        // Ottieni il colore selezionato dal ComboBox
        String selectedColor = colorComboBox.getValue();

        if (selectedColor != null) {
            scooter.setColor(selectedColor);
            updateConfigurationList("Color", selectedColor);
        } else {
            // In caso non sia selezionato un colore
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Color Selected");
            alert.setContentText("Please select a color for the scooter.");
            alert.showAndWait();
        }
    }




    // Metodo per configurare il motore
    @FXML
    private void configureEngine() {
        List<String> engines = List.of("Electric", "Gasoline", "Hybrid");
        ChoiceDialog<String> engineDialog = new ChoiceDialog<>(engines.get(0), engines);
        engineDialog.setTitle("Engine Configuration");
        engineDialog.setHeaderText("Select the engine type for your scooter");
        engineDialog.setContentText("Engine Type:");

        // Personalizzare l'icona
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/engine.png")));
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        engineDialog.setGraphic(icon);

        engineDialog.showAndWait().ifPresent(selectedEngine -> {
            scooter.setEngine(selectedEngine);
            updateConfigurationList("Engine", selectedEngine);
        });
    }

    // Metodo per configurare le ruote
    @FXML
    private void configureWheels() {
        List<String> wheels = List.of("Small", "Medium", "Large");
        ChoiceDialog<String> wheelsDialog = new ChoiceDialog<>(wheels.get(0), wheels);
        wheelsDialog.setTitle("Wheels Configuration");
        wheelsDialog.setHeaderText("Select the wheel size for your scooter");
        wheelsDialog.setContentText("Wheel Size:");

        // Personalizzare l'icona
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/wheels.png")));
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        wheelsDialog.setGraphic(icon);

        wheelsDialog.showAndWait().ifPresent(selectedWheels -> {
            scooter.setWheels(selectedWheels);
            updateConfigurationList("Wheels", selectedWheels);
        });
    }

    // Metodo per configurare la batteria
    @FXML
    private void configureBattery() {
        List<String> batteries = List.of("Standard", "Extended");
        ChoiceDialog<String> batteryDialog = new ChoiceDialog<>(batteries.get(0), batteries);
        batteryDialog.setTitle("Battery Configuration");
        batteryDialog.setHeaderText("Select the battery type for your scooter");
        batteryDialog.setContentText("Battery Type:");

        // Personalizzare l'icona
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/battery.png")));
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        batteryDialog.setGraphic(icon);

        batteryDialog.showAndWait().ifPresent(selectedBattery -> {
            scooter.setBattery(selectedBattery);
            updateConfigurationList("Battery", selectedBattery);
        });
    }

    // Metodo per configurare gli accessori
    @FXML
    private void configureAccessories() {
        List<String> accessories = List.of("Helmet", "Gloves", "Bag", "Lock");
        ChoiceDialog<String> accessoriesDialog = new ChoiceDialog<>(accessories.get(0), accessories);
        accessoriesDialog.setTitle("Accessories Configuration");
        accessoriesDialog.setHeaderText("Select accessories for your scooter");
        accessoriesDialog.setContentText("Accessories:");

        // Personalizzare l'icona
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/accessories.png")));
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        accessoriesDialog.setGraphic(icon);

        accessoriesDialog.showAndWait().ifPresent(selectedAccessory -> {
            updateConfigurationList("Accessories", selectedAccessory);
        });
    }

    // Metodo per visualizzare la configurazione finale
    @FXML
    private void showFinalConfiguration() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Final Configuration");
        alert.setHeaderText(null);

        StringBuilder configurationSummary = new StringBuilder("Your Scooter Configuration:\n");

        for (String config : configurationList.getItems()) {
            configurationSummary.append(config).append("\n");
        }

        alert.setContentText(configurationSummary.toString());
        alert.showAndWait();
    }
}