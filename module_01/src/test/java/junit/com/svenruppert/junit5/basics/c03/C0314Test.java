package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static java.time.Duration.ofMillis;

class C0314Test {
  @Disabled
  @Test
  void test001() {

    Assertions.assertTimeoutPreemptively(ofMillis(501), new Executable() {

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
