package it.didattica.cs.unicam.progettoloretimgc.controller;

import it.didattica.cs.unicam.progettoloretimgc.model.*;
import it.didattica.cs.unicam.progettoloretimgc.ontology.QueryService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScooterViewController {

    @FXML
    private ListView<String> configurationList;

    @FXML
    private ComboBox<String> colorComboBox, fuelComboBox, batteryComboBox, lightComboBox, windshieldComboBox, topcaseComboBox;

    private QueryService queryService;
    private Scooter scooter = new Scooter("MyScooter");

    // Mappa per gestire le configurazioni dinamicamente
    private final Map<ComboBox<String>, Configurator> configurators = new HashMap<>();

    public void setQueryService(QueryService queryService) {
        this.queryService = queryService;

        // Inizializza i dati che dipendono da QueryService
        if (queryService != null) {
            initializeConfigurations();
        }
    }

    @FXML
    private void initializeConfigurations() {
        // Configuratori per ogni ComboBox
        configurators.put(colorComboBox, () -> queryService.getColourComponents(scooter).stream()
                .map(Colour::getColourName).collect(Collectors.toList()));
        configurators.put(fuelComboBox, () -> queryService.getFuelComponents(scooter).stream()
                .map(Fuel::getFuelName).collect(Collectors.toList()));
        configurators.put(batteryComboBox, () -> queryService.getBatteryComponents(scooter).stream()
                .map(Battery::getBatteryCapacity).collect(Collectors.toList()));
        configurators.put(lightComboBox, () -> queryService.getLightComponents(scooter).stream()
                .map(Light::getLightDescription).collect(Collectors.toList()));
        configurators.put(windshieldComboBox, () -> queryService.getWindshieldComponents(scooter).stream()
                .map(Windshield::getWindshieldDescription).collect(Collectors.toList()));
        configurators.put(topcaseComboBox, () -> queryService.getTopCaseComponents(scooter).stream()
                .map(TopCase::getTopCaseDescription).collect(Collectors.toList()));

        // Popola le ComboBox
        configurators.forEach((comboBox, configurator) -> populateComboBox(comboBox, configurator));

        // Imposta un'opzione di default
        configurators.keySet().forEach(comboBox -> comboBox.setValue("None"));
    }

    private void populateComboBox(ComboBox<String> comboBox, Configurator configurator) {
        List<String> items = configurator.getOptions();
        if (comboBox.getItems().isEmpty()) {
            comboBox.setItems(FXCollections.observableArrayList("None"));
            comboBox.getItems().addAll(items);
        }
    }

    @FXML
    private void handleSelection(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        String selectedValue = comboBox.getValue();

        if (selectedValue != null && selectedValue.equals("None")) {
            if (isAccessoryComboBox(comboBox)) {
                // Rimuove solo l'accessorio specifico
                String accessoryType = getComponentName(comboBox);
                removeAccessoryFromList(accessoryType);
            } else {
                // Reimposta il valore precedente e mostra un messaggio di errore
                comboBox.setValue(comboBox.getItems().stream().filter(item -> !item.equals("None")).findFirst().orElse(null));
                showAlert("Invalid Selection", "The 'None' option is only allowed for accessories.");
            }
        } else if (selectedValue != null) {
            // Gestisci la selezione normale
            String componentName = getComponentName(comboBox);
            updateConfigurationList(componentName, selectedValue);
        } else {
            showAlert("No Selection", "Please select an option.");
        }
    }

    private boolean isAccessoryComboBox(ComboBox<String> comboBox) {
        return comboBox == windshieldComboBox || comboBox == topcaseComboBox;
    }

    private String getComponentName(ComboBox<String> comboBox) {
        if (comboBox == colorComboBox) {
            return "Colour";
        } else if (comboBox == fuelComboBox) {
            return "Fuel";
        } else if (comboBox == batteryComboBox) {
            return "Battery";
        } else if (comboBox == lightComboBox) {
            return "Light";
        } else if (comboBox == windshieldComboBox) {
            return "Windshield";
        } else if (comboBox == topcaseComboBox) {
            return "Topcase";
        }

        return "Unknown Component";
    }

    private void updateConfigurationList(String componentName, String componentValue) {
        String existingEntry = configurationList.getItems().stream()
                .filter(item -> item.startsWith(componentName + ":"))
                .findFirst()
                .orElse(null);

        if (existingEntry != null) {
            configurationList.getItems().remove(existingEntry);
        }

        // Aggiungi la nuova voce solo se il valore non è "None"
        if (!componentValue.equals("None")) {
            configurationList.getItems().add(componentName + ": " + componentValue);
        }

        // Gestisci il conflitto tra Fuel e Battery
        if (componentName.equals("Fuel")) {
            removeComponentFromList("Battery");
        } else if (componentName.equals("Battery")) {
            removeComponentFromList("Fuel");
        }

    }

    private void removeComponentFromList(String componentName) {
        String conflictingEntry = configurationList.getItems().stream()
                .filter(item -> item.startsWith(componentName + ":"))
                .findFirst()
                .orElse(null);

        if (conflictingEntry != null) {
            configurationList.getItems().remove(conflictingEntry);
        }
    }

    private void removeAccessoryFromList(String accessoryType) {
        String accessoryEntry = configurationList.getItems().stream()
                .filter(item -> item.startsWith("Accessory: " + accessoryType))
                .findFirst()
                .orElse(null);

        if (accessoryEntry != null) {
            configurationList.getItems().remove(accessoryEntry);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void showFinalConfiguration() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Final Configuration");
        alert.setHeaderText(null);

        Text boldText = new Text("Scooter Configuration:\n");
        boldText.setStyle("-fx-font-weight: bold;");

        StringBuilder configurationSummary = new StringBuilder();
        for (String config : configurationList.getItems()) {
            configurationSummary.append(config).append("\n");
        }
        Text normalText = new Text(configurationSummary.toString());

        TextFlow textFlow = new TextFlow(boldText, normalText);

        Label label = new Label();
        label.setGraphic(textFlow);
        label.setPrefWidth(400);
        label.setWrapText(true);

        alert.getDialogPane().setContent(label);
        alert.showAndWait();
    }

    @FunctionalInterface
    interface Configurator {
        List<String> getOptions();
    }
}