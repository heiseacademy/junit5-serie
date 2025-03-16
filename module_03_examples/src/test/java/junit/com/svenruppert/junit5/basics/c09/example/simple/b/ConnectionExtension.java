package junit.com.svenruppert.junit5.basics.c09.example.simple.b;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ConnectionExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    // Hier könnte z.B. eine Datenbankverbindung hergestellt oder ein bestimmter Zustand vorbereitet werden.
    System.out.println("Connection wird für den Test hergestellt...");
  }
}

