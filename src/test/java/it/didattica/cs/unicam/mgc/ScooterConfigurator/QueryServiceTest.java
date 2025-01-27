package it.didattica.cs.unicam.mgc.ScooterConfigurator;

import it.didattica.cs.unicam.mgc.ScooterConfigurator.model.Colour;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.model.Fuel;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.model.Light;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.model.Scooter;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.ontology.SPARQLQueryExecutor;
import it.didattica.cs.unicam.mgc.ScooterConfigurator.ontology.QueryService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.apache.jena.rdf.model.Literal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QueryServiceTest {

    private SPARQLQueryExecutor mockQueryExecutor;
    private QueryService queryService;
    private Scooter mockScooter;

    @BeforeEach
    public void setUp() {
        mockQueryExecutor = Mockito.mock(SPARQLQueryExecutor.class);
        queryService = new QueryService(mockQueryExecutor);
        mockScooter = Mockito.mock(Scooter.class);
    }

    @Test
    public void testGetColourComponents() {
        // Mocking ResultSet and QuerySolution
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        QuerySolution mockSolution = Mockito.mock(QuerySolution.class);
        Literal mockLiteral = mock(Literal.class);

        // Stubbing behavior
        when(mockQueryExecutor.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.hasNext()).thenReturn(true, false); // First call returns true, second false
        when(mockResultSet.nextSolution()).thenReturn(mockSolution);
        when(mockSolution.getLiteral("property")).thenReturn(mockLiteral);
        when(mockLiteral.getString()).thenReturn("Red"); // Return "Red" when getString() is called

        // Test the method
        List<Colour> colours = queryService.getColourComponents(mockScooter);

        // Assertions
        assertNotNull(colours);
        assertEquals(1, colours.size());
        assertEquals("Red", colours.get(0).getColourName());

        // Verify interactions
        verify(mockQueryExecutor).executeQuery(anyString());
        verify(mockResultSet, times(2)).hasNext();
        verify(mockResultSet).nextSolution();
        verify(mockSolution).getLiteral("property");
        verify(mockLiteral).getString(); // Verifies that getString() was called on the literal
    }



    @Test
public void testGetFuelComponents() {
    // Mocking ResultSet and QuerySolution
    ResultSet mockResultSet = Mockito.mock(ResultSet.class);
    QuerySolution mockSolution = Mockito.mock(QuerySolution.class);
    Literal mockLiteral = mock(Literal.class);

    // Stubbing behavior
    when(mockQueryExecutor.executeQuery(anyString())).thenReturn(mockResultSet);
    when(mockResultSet.hasNext()).thenReturn(true, false); // First call returns true, second false
    when(mockResultSet.nextSolution()).thenReturn(mockSolution);
    when(mockSolution.getLiteral("property")).thenReturn(mockLiteral);
    when(mockLiteral.getString()).thenReturn("Electric"); // Returns "Electric" when getString() is called

    // Test the method
    List<Fuel> fuels = queryService.getFuelComponents(mockScooter);

    // Assertions
    assertNotNull(fuels);
    assertEquals(1, fuels.size());
    assertEquals("Electric", fuels.get(0).getFuelName());

    // Verify interactions
    verify(mockQueryExecutor).executeQuery(anyString());
    verify(mockResultSet, times(2)).hasNext();
    verify(mockResultSet).nextSolution();
    verify(mockSolution).getLiteral("property");
    verify(mockLiteral).getString(); // Verifies that getString() was called on the literal
}

    @Test
    public void testGetLightComponents() {
        // Mocking ResultSet and QuerySolution
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        QuerySolution mockSolution = Mockito.mock(QuerySolution.class);
        Literal mockLiteral = mock(Literal.class);

        // Stubbing behavior
        when(mockQueryExecutor.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.hasNext()).thenReturn(true, false); // First call returns true, second false
        when(mockResultSet.nextSolution()).thenReturn(mockSolution);
        when(mockSolution.getLiteral("property")).thenReturn(mockLiteral);
        when(mockLiteral.getString()).thenReturn("LED"); // Returns "LED" when getString() is called

        // Test the method
        List<Light> lights = queryService.getLightComponents(mockScooter);

        // Assertions
        assertNotNull(lights);
        assertEquals(1, lights.size());
        assertEquals("LED", lights.get(0).getLightDescription());

        // Verify interactions
        verify(mockQueryExecutor).executeQuery(anyString());
        verify(mockResultSet, times(2)).hasNext();
        verify(mockResultSet).nextSolution();
        verify(mockSolution).getLiteral("property");
        verify(mockLiteral).getString(); // Verifies that getString() was called on the literal
    }

}
