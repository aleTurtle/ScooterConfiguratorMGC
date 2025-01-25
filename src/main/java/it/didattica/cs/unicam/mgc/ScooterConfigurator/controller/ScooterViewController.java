package it.didattica.cs.unicam.mgc.ScooterConfigurator.controller;

import it.didattica.cs.unicam.mgc.ScooterConfigurator.model.*;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.ontology.QueryService;
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
    private ComboBox<String> colorComboBox, fuelComboBox, batteryComboBox, lightComboBox, windshieldComboBox, topcaseComboBox,
            electricComboBox, ICEComboBox,seatComboBox,scooterPlateComboBox, wheelComboBox,brakeComboBox;

    private QueryService queryService;


    private Scooter scooter = new Scooter("MyScooter");

    // Map to manage configurations dynamically
    private final Map<ComboBox<String>, Configurator> configurators = new HashMap<>();

    public void setQueryService(QueryService queryService) {
        this.queryService = queryService;

        // Initialize data that depends on QueryService
        if (queryService != null) {
            initializeConfigurations();
        }
    }


    @FXML
    private void initializeConfigurations() {
        // Configurators for each ComboBox
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
        configurators.put(electricComboBox, () -> queryService.getElectricMotorComponents(scooter).stream()
                .map(ElectricMotor::getElectricPowerValue).collect(Collectors.toList()));
        configurators.put(ICEComboBox, () -> queryService.getInternalCombustionEngineComponents(scooter).stream()
                .map(InternalCombustionEngine::getICEvalue).collect(Collectors.toList()));
        configurators.put(seatComboBox, () -> queryService.getSeatComponents(scooter).stream()
                .map(Seat::getSeatDescription).collect(Collectors.toList()));
        configurators.put(scooterPlateComboBox, () -> queryService.getScooterPlateComponents(scooter).stream()
                .map(ScooterPlate::getScooterPlateIdentifier).collect(Collectors.toList()));
        configurators.put(wheelComboBox, () -> queryService.getWheelComponents(scooter).stream()
                .map(Wheel::getWheelDescription).collect(Collectors.toList()));
        configurators.put(brakeComboBox, () -> queryService.getBrakeComponents(scooter).stream()
                .map(Brake::getBrakeDescription).collect(Collectors.toList()));


        // it populates the ComboBoxes
        configurators.forEach((comboBox, configurator) -> populateComboBox(comboBox, configurator));

        // Set a default option
        configurators.keySet().forEach(comboBox -> comboBox.setValue("None"));
    }


    // it populates the combo boxes considering all list items as strings, but diversify them by type according to the box
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

        if (selectedValue != null) {
            // Handle selection
            String componentName = getComponentName(comboBox);
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
        } else if (comboBox == brakeComboBox) {
            return "Brake";
        } else if (comboBox == lightComboBox) {
            return "Light";
        } else if (comboBox == windshieldComboBox) {
            return "Accessory";
        } else if (comboBox == topcaseComboBox) {
            return "Accessory";
        }else if (comboBox == electricComboBox) {
            return "Electric Motor";
        }else if (comboBox == seatComboBox) {
            return "Seat";
        }else if (comboBox == scooterPlateComboBox) {
            return "ScooterPlate Number";
        }else if (comboBox == wheelComboBox) {
            return "Wheel";
        }else if (comboBox == ICEComboBox) {
            return "Internal Combustion Engine";
        }
        return "Unknown Component";
    }






    private void updateConfigurationList(String componentName, String componentValue) {
        // If the value is "None", directly remove the component from the list and exit
        if (componentValue.equals("None")) {
            removeComponentFromList(componentName);
            return;
        }

        // Multiple accessories can exist simultaneously
        if (componentValue.equals("Windshield") || componentName.equals("Topcase")) {
            configurationList.getItems().add(componentName + ": " + componentValue);
            return;
        }
        // Search for an existing entry and remove it
        String existingEntry = configurationList.getItems().stream()
                .filter(item -> item.startsWith(componentName + ":"))
                .findFirst()
                .orElse(null);

        if (existingEntry != null) {
            configurationList.getItems().remove(existingEntry);
        }

        // Add the new entry
        configurationList.getItems().add(componentName + ": " + componentValue);

        // Manage conflict between Fuel and Battery
        if (componentName.equals("Fuel")) {
            removeComponentFromList("Battery");
        } else if (componentName.equals("Battery")) {
            removeComponentFromList("Fuel");
        }

        // it manages conflict between Internal Combustion Engine and Electric Motor
        if (componentName.equals("Internal Combustion Engine")) {
            removeComponentFromList("Electric Motor");
        } else if (componentName.equals("Electric Motor")) {
            removeComponentFromList("Internal Combustion Engine");
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


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void showFinalConfiguration() {
        this.checkListCompatibility();

        // it shows the final configuration
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

    private void checkListCompatibility(){
        // Flags to detect conflicts
        boolean hasFuel = false;
        boolean hasElectricMotor = false;
        boolean hasBattery = false;
        boolean hasICE = false;

        // it iterates over the configuration list
        for (String config : configurationList.getItems()) {
            if (config.startsWith("Fuel:")) {
                hasFuel = true;
            } else if (config.startsWith("Electric Motor:")) {
                hasElectricMotor = true;
            } else if (config.startsWith("Battery:")) {
                hasBattery = true;
            } else if (config.startsWith("Internal Combustion Engine:")) {
                hasICE = true;
            }
        }

        // Check conflicts
        if ((hasFuel && hasElectricMotor) || (hasBattery && hasICE)) {
            showAlert("Configuration Conflict",
                    "Conflicting components detected:\n" +
                            "- Fuel and Electric Motor cannot coexist.\n" +
                            "- Battery and Internal Combustion Engine cannot coexist.\n" +
                            "  Please change your configuration");
            return; // Interrupt display if there is a conflict
        }

    }

    @FXML
    private void resetList() {
        // Clear the ListView to remove all configurations
        configurationList.getItems().clear();
    }


    @FunctionalInterface
    interface Configurator {
        List<String> getOptions();
    }
}
