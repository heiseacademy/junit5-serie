package junit.com.svenruppert.junit5.basics.c08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class C0803CTest {

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.METHOD, ElementType.TYPE})
  @ExtendWith(DemoExceptionHandler.class)
  public @interface IgnoreNPE {
  }

  public static class DemoExceptionHandler
      implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context,
                                             Throwable throwable)
        throws Throwable {

      // Logik zur Ausnahmebehandlung
      System.out.println("Test failed: " + context.getDisplayName());
      System.out.println("Exception: " + throwable.getMessage());

      // Beispiel: Übersetze die Ausnahme in eine andere oder ignoriere sie
      if (throwable instanceof RuntimeException) {
        System.out.println("RuntimeException wurde abgefangen. Habe doch keine Lust dazu.");
      } else {
        // Andere Ausnahmen weiterleiten
        throw throwable;
      }
    }
  }


  @IgnoreNPE
  public static class DemoClass {
    @Test
    void test001() {
      System.out.println("test001");
      throw new NullPointerException("NPE - weil ich Lust dazu habe");
    }
  }

}