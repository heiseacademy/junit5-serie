package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static java.time.Duration.ofMillis;

public class C0313Test {

  @Test
  void test001() {

    Assertions.assertTimeout(ofMillis(500), new Executable() {

      @Override
      public void execute() throws Throwable {
        try {
          Thread.sleep(500);
          System.out.println("Action done!");
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });
  }
}
