package it.didattica.cs.unicam.progettoloretimgc.ontology;

import it.didattica.cs.unicam.progettoloretimgc.model.*;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class QueryService {
    private static SPARQLQueryExecutor queryExecutor;

    public QueryService(SPARQLQueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    // Metodo generico per ottenere i componenti
    public <T> List<T> getComponents(Scooter scooter, String componentType, String sparqlQuery) {
        List<T> componentList = new ArrayList<>();
        ResultSet results = queryExecutor.executeQuery(sparqlQuery);

        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            T component = createComponent(scooter, componentType, solution);
            componentList.add(component);
        }

        return componentList;
    }

    // Metodo per creare il componente in base al tipo di componente e ai dati della query
    public <T> T createComponent(Scooter scooter, String componentType, QuerySolution solution) {
        T component = null;
        switch (componentType) {
            case "Colour" -> component = (T) new Colour(scooter, solution.getLiteral("property").getString());
            case "Fuel" -> component = (T) new Fuel(scooter, solution.getLiteral("property").getString());
            case "Light" -> component = (T) new Light(scooter, solution.getLiteral("property").getString());
            case "Seat" -> component = (T) new Seat(scooter, solution.getLiteral("property").getString());
            case "ScooterPlate" -> component = (T) new ScooterPlate(scooter, solution.getLiteral("property").getString());
            case "Battery" -> component = (T) new Battery(scooter, Double.toString(solution.getLiteral("property").getDouble()));
            case "Windshield" -> component = (T) new Windshield(scooter, solution.getLiteral("property").getString());
            case "TopCase" -> component = (T) new TopCase(scooter, solution.getLiteral("property").getString());
            case "ElectricMotor" -> component = (T) new ElectricMotor(scooter, Double.toString(solution.getLiteral("property").getDouble()));
            case "InternalCombustionEngine" -> component = (T) new InternalCombustionEngine(scooter, Integer.toString(solution.getLiteral("property").getInt()));
            default -> throw new IllegalArgumentException("Unknown component type: " + componentType);
        }
        return component;
    }


    // Costruzione dinamica della query
    private String buildQuery(String componentType, String property) {
        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX scooter: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#>" +
                "SELECT ?component ?property WHERE { " +
                "?component rdf:type scooter:" + componentType + " . " +
                "?component scooter:" + property + " ?property . " +
                "}";

        return sparqlQuery;
    }

    // Metodo specifico per ogni componente
    public List<Colour> getColourComponents(Scooter scooter) {
        String Colour = "Colour";
        String HasColourName = "HasColourName";
        String sparqlQuery= this.buildQuery(Colour,HasColourName);
        return getComponents(scooter, "Colour", sparqlQuery);
    }

    public List<Fuel> getFuelComponents(Scooter scooter) {
        String Fuel = "Fuel";
        String HasFuelName = "HasFuelName";
        String sparqlQuery= this.buildQuery(Fuel,HasFuelName);
        return getComponents(scooter, "Fuel", sparqlQuery);
    }

    public List<Light> getLightComponents(Scooter scooter) {
        String Light = "Light";
        String HasLightDescription = "HasLightDescription";
        String sparqlQuery= this.buildQuery(Light,HasLightDescription);
        return getComponents(scooter, "Light", sparqlQuery);
    }

    public List<Seat> getSeatComponents(Scooter scooter) {
        String Seat = "Seat";
        String IsMadeOf = "IsMadeOf";
        String sparqlQuery= this.buildQuery(Seat,IsMadeOf);
        return getComponents(scooter, "Seat", sparqlQuery);
    }

    public List<ScooterPlate> getScooterPlateComponents(Scooter scooter) {
        String ScooterPlate = "ScooterPlate";
        String HasScooterPlateNumber = "HasScooterPlateNumber";
        String sparqlQuery= this.buildQuery(ScooterPlate,HasScooterPlateNumber);
        return getComponents(scooter, "ScooterPlate", sparqlQuery);
    }

    public List<Battery> getBatteryComponents(Scooter scooter) {
        String Battery = "Battery";
        String HasBatteryCapacity  = "HasBatteryCapacity ";
        String sparqlQuery= this.buildQuery(Battery,HasBatteryCapacity );
        return getComponents(scooter, "Battery", sparqlQuery);
    }

    public List<Windshield> getWindshieldComponents(Scooter scooter) {
        String Windshield = "Windshield";
        String HasAccessoryDescription  = "HasAccessoryDescription ";
        String sparqlQuery= this.buildQuery(Windshield,HasAccessoryDescription );
        return getComponents(scooter, "Windshield", sparqlQuery);
    }

    public List<TopCase> getTopCaseComponents(Scooter scooter) {
        String TopCase = "TopCase";
        String HasAccessoryDescription = "HasAccessoryDescription";
        String sparqlQuery= this.buildQuery(TopCase,HasAccessoryDescription);
        return getComponents(scooter, "TopCase", sparqlQuery);
    }

    public List<ElectricMotor> getElectricMotorComponents(Scooter scooter) {
        String Engine  = "Engine ";
        String HasMotorPower = "HasMotorPower";
        String sparqlQuery= this.buildQuery(Engine ,HasMotorPower  );
        return getComponents(scooter, "ElectricMotor", sparqlQuery);
    }

    public List<InternalCombustionEngine> getInternalCombustionEngineComponents(Scooter scooter) {
        String Engine  = "Engine ";
        String HasEngineDisplacement ="HasEngineDisplacement ";
        String sparqlQuery= this.buildQuery(Engine ,HasEngineDisplacement   );
        return getComponents(scooter, "InternalCombustionEngine", sparqlQuery);
    }
}
