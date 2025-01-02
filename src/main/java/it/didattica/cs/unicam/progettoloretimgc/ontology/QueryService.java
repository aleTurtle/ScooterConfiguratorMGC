package it.didattica.cs.unicam.progettoloretimgc.ontology;

import it.didattica.cs.unicam.progettoloretimgc.Colour;
import it.didattica.cs.unicam.progettoloretimgc.Scooter;
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
                "PREFIX laptop: <http://www.semanticweb.org/leona/ontologies/2024/9/LaptopConfiguratorModellazioneGestioneConoscenza#>" +
                "SELECT ?colour ?name WHERE { " +
                "?colour rdf:type laptop:Colour . " +
                "?colour laptop:HasColourName ?name . " +
                "}";

        ResultSet results = queryExecutor.executeQuery(sparqlQuery);
        while (results != null && results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            Resource colourResource = solution.getResource("colour");
            String name = colourResource.getLocalName();
            colourList.add(new Colour(scooter, name));
        }
        return colourList;
    }




}
