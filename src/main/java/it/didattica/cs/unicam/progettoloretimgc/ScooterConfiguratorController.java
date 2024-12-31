package it.didattica.cs.unicam.progettoloretimgc;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class ScooterConfiguratorController {

    @FXML
    private ListView<String> configurationList;

    @FXML
    private Tab colorTab, engineTab, wheelsTab, batteryTab, accessoriesTab;

    // Simulazione di un oggetto Scooter e delle sue configurazioni
    private Scooter scooter = new Scooter("MyScooter");

    // Metodo per aggiungere un componente alla lista di configurazione
    private void updateConfigurationList(String componentName, String componentValue) {
        configurationList.getItems().add(componentName + ": " + componentValue);
    }

    // Metodo per configurare il colore
    @FXML
    private void configureColor() {
        List<String> colors = List.of("Red", "Blue", "Green", "Yellow", "Black");
        ChoiceDialog<String> colorDialog = new ChoiceDialog<>(colors.get(0), colors);
        colorDialog.setTitle("Color Configuration");
        colorDialog.setHeaderText("Select the color for your scooter");
        colorDialog.setContentText("Color:");

        // Personalizzare il dialogo con un'icona
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/color.png")));
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        colorDialog.setGraphic(icon);

        colorDialog.showAndWait().ifPresent(selectedColor -> {
            scooter.setColor(selectedColor);
            updateConfigurationList("Color", selectedColor);
        });
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
