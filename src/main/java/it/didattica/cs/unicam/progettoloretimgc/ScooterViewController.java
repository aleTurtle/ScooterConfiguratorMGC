package it.didattica.cs.unicam.progettoloretimgc;

import it.didattica.cs.unicam.progettoloretimgc.ontology.QueryService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ScooterViewController {

    @FXML
    private ListView<String> configurationList;

    @FXML
    private ComboBox<String> colorComboBox, fuelComboBox, batteryComboBox, lightComboBox, windshieldComboBox;

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

        if (selectedValue != null && !selectedValue.equals("None")) {
            // Ottieni il nome del componente leggibile
            String componentName = getComponentName(comboBox);
            // Aggiorna la lista con il nome del componente e il valore selezionato
            updateConfigurationList(componentName, selectedValue);
        } else {
            showAlert("No Selection", "Please select an option.");
        }
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
            return "Accessory";
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

        configurationList.getItems().add(componentName + ": " + componentValue);
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

        StringBuilder configurationSummary = new StringBuilder("Your Scooter Configuration:\n");

        for (String config : configurationList.getItems()) {
            configurationSummary.append(config).append("\n");
        }

        alert.setContentText(configurationSummary.toString());
        alert.showAndWait();
    }

    @FunctionalInterface
    interface Configurator {
        List<String> getOptions();
    }
}
