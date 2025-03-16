package junit.com.svenruppert.junit5.basics.c09.example.simple.a;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SimpleBeforeAllExtension
    implements BeforeAllCallback {
  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    System.out.println("Vorbereitung vor allen Tests dieser Klasse...");
  }
}

