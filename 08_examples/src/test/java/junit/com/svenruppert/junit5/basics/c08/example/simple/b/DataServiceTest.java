package junit.com.svenruppert.junit5.basics.c08.example.simple.b;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DataServiceExtension.class)
public class DataServiceTest {

  @Test
  void testDataFetching(DataServiceExtension.DataService dataService) {
    // Dank ParameterResolver kommt das DataService-Objekt direkt als Parameter
    String data = dataService.fetchRandomData();
    assertNotNull(data);
    System.out.println("Erhaltene Daten: " + data);
  }

  @Test
  void testDataConsistency(DataServiceExtension.DataService dataService) {
    // Hier kann erneut dieselbe DataService-Instanz genutzt werden
    String data = dataService.fetchRandomData();
    assertTrue(data.startsWith("Data-"));
    System.out.println("Überprüfte Datenkonsistenz: " + data);
  }
}

