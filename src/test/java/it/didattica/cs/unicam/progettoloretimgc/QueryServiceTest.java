package it.didattica.cs.unicam.progettoloretimgc;

import it.didattica.cs.unicam.progettoloretimgc.model.*;
import it.didattica.cs.unicam.progettoloretimgc.ontology.QueryService;
import it.didattica.cs.unicam.progettoloretimgc.ontology.SPARQLQueryExecutor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryServiceTest {

    private SPARQLQueryExecutor queryExecutor;
    private QueryService queryService;
    private Scooter testScooter;

    @BeforeEach
    void setUp() {
        queryExecutor = mock(SPARQLQueryExecutor.class);
        queryService = new QueryService(queryExecutor);
        testScooter = new Scooter("TestScooter");
    }

    @Test
    void testGetColourComponents() {
        // Mock the ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.hasNext()).thenReturn(true, false);

        QuerySolution solution = mock(QuerySolution.class);
        when(resultSet.nextSolution()).thenReturn(solution);
        when(solution.getLiteral("property").getString()).thenReturn("Red");

        // Mock the SPARQL query execution
        when(queryExecutor.executeQuery(anyString())).thenReturn(resultSet);

        // Execute the method
        List<Colour> colours = queryService.getColourComponents(testScooter);

        // Verify the results
        assertNotNull(colours);
        assertEquals(1, colours.size());
        assertEquals("Red", colours.get(0).getColourName());
        verify(queryExecutor, times(1)).executeQuery(anyString());
    }

    @Test
    void testGetFuelComponents() {
        // Mock the ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.hasNext()).thenReturn(true, true, false);

        QuerySolution solution1 = mock(QuerySolution.class);
        QuerySolution solution2 = mock(QuerySolution.class);

        when(resultSet.nextSolution()).thenReturn(solution1, solution2);
        when(solution1.getLiteral("property").getString()).thenReturn("Petrol");
        when(solution2.getLiteral("property").getString()).thenReturn("Diesel");

        // Mock the SPARQL query execution
        when(queryExecutor.executeQuery(anyString())).thenReturn(resultSet);

        // Execute the method
        List<Fuel> fuels = queryService.getFuelComponents(testScooter);

        // Verify the results
        assertNotNull(fuels);
        assertEquals(2, fuels.size());
        assertEquals("Petrol", fuels.get(0).getFuelName());
        assertEquals("Diesel", fuels.get(1).getFuelName());
        verify(queryExecutor, times(1)).executeQuery(anyString());
    }

    @Test
    void testGetBatteryComponents() {
        // Mock the ResultSet
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.hasNext()).thenReturn(true, false);

        QuerySolution solution = mock(QuerySolution.class);
        when(resultSet.nextSolution()).thenReturn(solution);
        when(solution.getLiteral("property").getDouble()).thenReturn(500.0);

        // Mock the SPARQL query execution
        when(queryExecutor.executeQuery(anyString())).thenReturn(resultSet);

        // Execute the method
        List<Battery> batteries = queryService.getBatteryComponents(testScooter);

        // Verify the results
        assertNotNull(batteries);
        assertEquals(1, batteries.size());
        assertEquals("500.0", batteries.get(0).getBatteryCapacity());
        verify(queryExecutor, times(1)).executeQuery(anyString());
    }
}
