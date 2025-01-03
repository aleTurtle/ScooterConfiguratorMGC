package it.didattica.cs.unicam.progettoloretimgc.ontology;

import it.didattica.cs.unicam.progettoloretimgc.*;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryService {
    private static SPARQLQueryExecutor queryExecutor;

    public QueryService(SPARQLQueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }




    public List<Colour> getColourComponents(Scooter scooter) {
        List<Colour> colourList = new ArrayList<>();

        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX scooter: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#>" +
                "SELECT ?colour ?name WHERE { " +
                "?colour rdf:type scooter:Colour . " +
                "?colour scooter:HasColourName ?name . " +
                "}";

        ResultSet results = queryExecutor.executeQuery(sparqlQuery);
        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            Resource colourResource = solution.getResource("colour");
            String name = solution.getLiteral("name").getString();
            colourList.add(new Colour(scooter, name));
        }
        return colourList;
    }

    public List<Fuel> getFuelComponents(Scooter scooter) {
        List<Fuel> fuelList = new ArrayList<>();

        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX scooter: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#>" +
                "SELECT ?fuel ?name WHERE { " +
                "?fuel rdf:type scooter:Fuel . " +
                "?fuel scooter:HasFuelName ?name . " +
                "}";

        ResultSet results = queryExecutor.executeQuery(sparqlQuery);
        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            Resource fuelResource = solution.getResource("fuel");
            String name = solution.getLiteral("name").getString();
            fuelList.add(new Fuel(scooter, name));
        }
        return fuelList;
    }

    public List<Light> getLightComponents(Scooter scooter) {
        List<Light> lightList = new ArrayList<>();

        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX scooter: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#>" +
                "SELECT ?light ?description WHERE { " +
                "?light rdf:type scooter:Light . " +
                "?light scooter:HasLightDescription ?description . " +
                "}";

        ResultSet results = queryExecutor.executeQuery(sparqlQuery);
        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            Resource fuelResource = solution.getResource("light");
            String description = solution.getLiteral("description").getString();
            lightList.add(new Light(scooter, description));
        }
        return lightList;
    }


    public List<Battery> getBatteryComponents(Scooter scooter) {
        List<Battery> batteryList = new ArrayList<>();

        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX scooter: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#>" +
                "SELECT ?battery ?capacity WHERE { " +
                "?battery rdf:type scooter:Battery . " +
                "?battery scooter:HasBatteryCapacity ?capacity . " +
                "}";

        ResultSet results = queryExecutor.executeQuery(sparqlQuery);
        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            Resource batteryResource = solution.getResource("battery");
            // Recupera il valore come double e lo converte in stringa
            double capacityValue = solution.getLiteral("capacity").getDouble();
            String capacity = Double.toString(capacityValue);
            batteryList.add(new Battery(scooter, capacity));
        }
        return batteryList;
    }

    public List<Windshield> getWindshieldComponents(Scooter scooter) {
        List<Windshield> windshieldList = new ArrayList<>();

        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX scooter: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#>" +
                "SELECT ?windshield ?description WHERE { " +
                "?windshield rdf:type scooter:Windshield . " +
                "?windshield scooter:HasAccessoryDescription ?description . " +
                "}";

        ResultSet results = queryExecutor.executeQuery(sparqlQuery);
        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            Resource fuelResource = solution.getResource("windshield");
            String description = solution.getLiteral("description").getString();
            windshieldList.add(new Windshield(scooter, description));
        }
        return windshieldList;

    }

/*
    public List<Accessory> getAccessoryComponents(Scooter scooter) {
        List<Accessory> accessoriesList = new ArrayList<>();

        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX scooter: <http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#>" +
                "SELECT ?accessory ?name WHERE { " +
                "?fuel rdf:type scooter:Fuel . " +
                "?fuel scooter:HasFuelName ?name . " +
                "}";

        ResultSet results = queryExecutor.executeQuery(sparqlQuery);
        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            Resource fuelResource = solution.getResource("fuel");
            String name = solution.getLiteral("name").getString();
            fuelList.add(new Fuel(scooter, name));
        }
        return fuelList;
    }

*/

}
