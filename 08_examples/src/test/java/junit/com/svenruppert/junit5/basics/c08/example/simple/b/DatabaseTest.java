package junit.com.svenruppert.junit5.basics.c08.example.simple.b;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ConnectionExtension.class)
public class DatabaseTest {

  @Test
  void testDataRetrieval() {
    System.out.println("Test für Datenabfrage");
    // Hier würde man nun auf die zuvor hergestellte Connection zugreifen.
  }

  @Test
  void testDataInsertion() {
    System.out.println("Test für Dateneinfügen");
  }
}

