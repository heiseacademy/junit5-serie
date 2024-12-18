package junit.com.svenruppert.junit5.basics.c08.example.simple.d;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ClassScopedResourceExtension
    implements
    BeforeAllCallback,
    BeforeEachCallback,
    AfterAllCallback {

  private static final String RESOURCE_KEY = "CLASS_RESOURCE";

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    // Einmalige Initialisierung einer Ressource auf Klassenebene
    Connection connection = new Connection("my-test-db");
    context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestClass()))
        .put(RESOURCE_KEY, connection);
    System.out.println("Verbindung für Testklasse hergestellt");
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    // Vor jedem Test die Ressource abrufen, um sicherzugehen, dass sie vorhanden ist
    Connection connection = context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestClass()))
        .get(RESOURCE_KEY, Connection.class);
    if (connection != null) {
      System.out.println("Verbindung im Test verfügbar: " + connection);
    }
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    // Am Ende alle Ressourcen schließen
    Connection connection = context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestClass()))
        .get(RESOURCE_KEY, Connection.class);
    if (connection != null) {
      connection.close();
      System.out.println("Verbindung für Testklasse geschlossen");
    }
  }

  // Beispielhafte Connection-Klasse
  static class Connection {
    private final String name;

    public Connection(String name) {
      this.name = name;
    }

    public void close() {
      System.out.println("Schließe Verbindung: " + name);
    }

    @Override
    public String toString() {
      return "Connection[" + name + "]";
    }
  }
}

