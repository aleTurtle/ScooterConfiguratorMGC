package it.didattica.cs.unicam.progettoloretimgc;

import it.didattica.cs.unicam.progettoloretimgc.ontology.OntologyLoader;
import org.apache.jena.ontology.OntModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OntologyLoaderTest {
    private OntologyLoader ontologyLoader;

    @BeforeEach
    public void setUp() {
        // Inizializza l'istanza di OntologyLoader con un file valido
        ontologyLoader = new OntologyLoader("ScooterOntology.rdf");
    }

    @Test
    public void testLoadOntologyFromFilePath() {
        // Carica l'ontologia dal file path specificato
        OntModel model = ontologyLoader.getOntologyModel();

        // Verifica che il modello non sia nullo
        assertNotNull(model, "Il modello dell'ontologia non dovrebbe essere nullo");

        // Verifica che il modello contenga delle dichiarazioni (tripli RDF)
        assertTrue(model.size() > 0, "Il modello dovrebbe contenere delle dichiarazioni RDF");

        // Verifica che il modello contenga una specifica classe (ad esempio, scooter:Colour)
        String namespace = "http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#";
        assertTrue(model.containsResource(model.createResource(namespace + "Scooter")), "Il modello dovrebbe contenere la risorsa 'Scooter'");
    }

    @Test
    public void testLoadInvalidOntology() {
        // Test con un path di ontologia non valido
        ontologyLoader = new OntologyLoader("InvalidOntology.rdf");
        OntModel model = ontologyLoader.getOntologyModel();

        // Verifica che il modello sia nullo o vuoto in caso di file non valido
        assertNotNull(model, "Il modello dell'ontologia non dovrebbe essere nullo anche se il file non è valido");
        assertEquals(0, model.size(), "Il modello dovrebbe essere vuoto se il file non è valido");
    }
}
