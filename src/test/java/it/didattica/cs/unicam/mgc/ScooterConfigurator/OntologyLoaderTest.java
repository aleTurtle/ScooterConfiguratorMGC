package it.didattica.cs.unicam.mgc.ScooterConfigurator;

import it.didattica.cs.unicam.mgc.ScooterConfigurator.ontology.OntologyLoader;
import org.apache.jena.ontology.OntModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OntologyLoaderTest {
    private OntologyLoader ontologyLoader;

    @BeforeEach
    public void setUp() {
        // it initializes the OntologyLoader instance with a valid file
        ontologyLoader = new OntologyLoader("ScooterOntology.rdf");
    }

    @Test
    public void testLoadOntologyFromFilePath() {
        // it loads the ontology from the specified file path
        OntModel model = ontologyLoader.getOntologyModel();

        // it verifies that the model is not null
        assertNotNull(model, "The ontology model should not be null");

        // it verifies  that the model contains statements (RDF triples)
        assertTrue(model.size() > 0, "The model should contain RDF statements");

        // it verifies  that the model contains a specific class (e.g., scooter:Colour)
        String namespace = "http://www.semanticweb.org/aless/ontologies/2024/ScooterConfigurator#";
        assertTrue(model.containsResource(model.createResource(namespace + "Scooter")), "The model should contain the resource 'Scooter'");
    }

    @Test
    public void testLoadInvalidOntology() {
        // Test with an invalid ontology path
        ontologyLoader = new OntologyLoader("InvalidOntology.rdf");
        OntModel model = ontologyLoader.getOntologyModel();

        // it verifies that the model is null or empty in case of an invalid file
        assertNotNull(model, "The ontology model should not be null even if the file is invalid");
        assertEquals(0, model.size(), "The model should be empty if the file is invalid");
    }
}
