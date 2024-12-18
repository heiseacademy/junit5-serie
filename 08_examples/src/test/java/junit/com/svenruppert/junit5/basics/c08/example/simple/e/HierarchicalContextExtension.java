package junit.com.svenruppert.junit5.basics.c08.example.simple.e;

import org.junit.jupiter.api.extension.*;

public class HierarchicalContextExtension
    implements BeforeAllCallback, BeforeEachCallback {

  private static final String CLASS_KEY = "CLASS_INFO";

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    // Auf Klassenebene Informationen speichern
    context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestClass()))
        .put(CLASS_KEY, "Klassenweite Info");
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    // Vom Testkontext aus auf den Elternkontext (Klassenkontext) zugreifen:
    ExtensionContext parentContext = context.getParent().orElseThrow(() -> new IllegalStateException("No parent context"));
    String classInfo = parentContext.getStore(ExtensionContext.Namespace.create(parentContext.getRequiredTestClass()))
        .get(CLASS_KEY, String.class);
    System.out.println("Vor jedem Test steht die klassenweite Info zur Verfügung: " + classInfo);
  }
}

