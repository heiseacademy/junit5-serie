package junit.com.svenruppert.junit5.basics.c09.example.simple.c;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestInfoLoggingExtension implements BeforeEachCallback {
  @Override
  public void beforeEach(ExtensionContext context) {
    String methodName = context.getRequiredTestMethod().getName();
    System.out.println("Starte Test: " + methodName);
  }
}

