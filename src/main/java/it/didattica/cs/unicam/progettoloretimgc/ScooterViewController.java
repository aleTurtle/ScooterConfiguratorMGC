package it.didattica.cs.unicam.progettoloretimgc;

import it.didattica.cs.unicam.progettoloretimgc.ontology.QueryService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.stream.Collectors;

public class ScooterViewController {

    @FXML
    private ListView<String> configurationList;

    @FXML
    private ComboBox<String> colorComboBox,fuelComboBox,batteryComboBox, lightComboBox; // ComboBox per la selezione del colore
    private QueryService queryService;

    @FXML
    private Tab colorTab, engineTab, wheelsTab,fuelSystemTab, lightsTab, accessoriesTab;

    // Simulazione di un oggetto Scooter e delle sue configurazioni
    private Scooter scooter= new Scooter("MyScooter");


    // Costruttore per iniettare il QueryService
    public ScooterViewController() {
    }

    // Metodo per impostare QueryService
    public void setQueryService(QueryService queryService) {
        this.queryService = queryService;

        // Inizializza i dati che dipendono da QueryService
        if (queryService != null) {
            initializeConfigurations();
        }
    }

    private void initializeConfigurations() {
        configureColors();
        configureFuels();
        configureBatteries();
        configureLights();
    }


    // Recupera la lista dei colori tramite il queryService
    private void configureColors() {
        List<Colour> colorComponents = queryService.getColourComponents(scooter);
        List<String> colorNames = colorComponents.stream()
                .map(Colour::getColourName)
                .collect(Collectors.toList());

        if (colorComboBox.getItems().isEmpty()) {
            colorComboBox.setItems(FXCollections.observableArrayList(colorNames));
        }
    }


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


    private void configureLights() {
        List<Light> lightComponents = queryService.getLightComponents(scooter);

        // Estrai i nomi dei colori dalla lista di oggetti Colour
        List<String> lightDescriptions = lightComponents.stream()
                .map(Light::getLightDescription) // Recupera il nome del colore
                .collect(Collectors.toList());

        // Imposta la lista dei colori nel ComboBox
        if (lightComboBox.getItems().isEmpty()) {
            lightComboBox.setItems(FXCollections.observableArrayList(lightDescriptions));
        }
    }


    @FXML
    private void configureLight(){
        // Ottieni il colore selezionato dal ComboBox
        String selectedLight = lightComboBox.getValue();

        if (selectedLight != null) {
            scooter.setLight(selectedLight);
            updateConfigurationList("Light", selectedLight);
        } else {
            // In caso non sia selezionato un colore
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Fuel Selected");
            alert.setContentText("Please select a fuel for the scooter.");
            alert.showAndWait();
        }

    }


    // Metodo per aggiungere un componente alla lista di configurazione
    private void updateConfigurationList(String componentName, String componentValue) {
        // Cerca e rimuove un elemento esistente con lo stesso tipo di componente
        String existingEntry = configurationList.getItems().stream()
                .filter(item -> item.startsWith(componentName + ":"))
                .findFirst()
                .orElse(null);

        if (existingEntry != null) {
            configurationList.getItems().remove(existingEntry);
        }

        // Aggiungi la nuova voce
        configurationList.getItems().add(componentName + ": " + componentValue);
    }


    @FXML
    private void initialize() {
    }

    private void configureFuels() {
        List<Fuel> fuelComponents = queryService.getFuelComponents(scooter);

        // Estrai i nomi dei colori dalla lista di oggetti Colour
        List<String> fuelNames = fuelComponents.stream()
                .map(Fuel::getFuelName) // Recupera il nome del colore
                .collect(Collectors.toList());

        // Imposta la lista dei colori nel ComboBox
        if (fuelComboBox.getItems().isEmpty()) {
            fuelComboBox.setItems(FXCollections.observableArrayList(fuelNames));
        }
    }


    @FXML
    private void configureFuel(){
        // Ottieni il colore selezionato dal ComboBox
        String selectedFuel = fuelComboBox.getValue();

        if (selectedFuel != null) {
            scooter.setFuel(selectedFuel);
            updateConfigurationList("Fuel", selectedFuel);
        } else {
            // In caso non sia selezionato un colore
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Fuel Selected");
            alert.setContentText("Please select a fuel for the scooter.");
            alert.showAndWait();
        }

    }

    private void configureBatteries() {
        List<Battery> batteryComponents = queryService.getBatteryComponents(scooter);

        // Estrai i nomi dei colori dalla lista di oggetti Colour
        List<String> batteryCapacities = batteryComponents.stream()
                .map(Battery::getBatteryCapacity) // Recupera il nome del colore
                .collect(Collectors.toList());

        // Imposta la lista dei colori nel ComboBox
        if (fuelComboBox.getItems().isEmpty()) {
            fuelComboBox.setItems(FXCollections.observableArrayList(batteryCapacities));
        }
    }

    // Metodo per configurare la batteria
    @FXML
    private void configureBattery() {
        // Ottieni il colore selezionato dal ComboBox
        String selectedBattery = batteryComboBox.getValue();

        if (selectedBattery != null) {
            scooter.setBattery(selectedBattery);
            updateConfigurationList("Battery", selectedBattery);
        } else {
            // In caso non sia selezionato un battery
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Battery Selected");
            alert.setContentText("Please select a battery for the scooter.");
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