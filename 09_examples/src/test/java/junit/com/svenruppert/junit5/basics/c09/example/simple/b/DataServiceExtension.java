package junit.com.svenruppert.junit5.basics.c09.example.simple.b;

import org.junit.jupiter.api.extension.*;
import java.util.UUID;

public class DataServiceExtension
    implements BeforeAllCallback, ParameterResolver {

  private static final String KEY = "DATA_SERVICE";

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    // Komplexe Initialisierung, z. B. Aufbau einer Verbindung zum Backend,
    // Konfiguration von Caches, Laden von Testdaten etc.
    DataService dataService = new DataService("jdbc:example://localhost:1234/testdb");

    // Das komplexe Objekt im Store hinterlegen, damit es später genutzt werden kann
    context.getStore(ExtensionContext.Namespace.GLOBAL).put(KEY, dataService);
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    // Überprüfen, ob der Parameter vom Typ DataService ist
    return parameterContext.getParameter().getType().equals(DataService.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    // Das DataService-Objekt aus dem Store wieder abrufen
    return extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
        .get(KEY, DataService.class);
  }

  // Beispiel für eine interne, komplexe Klasse, die irgendetwas mit Daten macht
  static class DataService {
    private final String connectionString;

    public DataService(String connectionString) {
      this.connectionString = connectionString;
      // Hier könnte z. B. eine echte DB-Verbindung geöffnet werden
    }

    public String fetchRandomData() {
      // Stellt hier symbolisch eine komplexe Datenoperation dar
      return "Data-" + UUID.randomUUID().toString();
    }
  }
}

