package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.fail;

public class C0318Test {

  @Disabled
  @Test
  void test001() {
    fail("Not yet implemented");
  }

  @Disabled
  @Test
  void test002() {
    try {
      new DemoClass().doSomething();
      fail("Sollte nie passieren");
    } catch (RuntimeException e) {
      Assertions.assertEquals("Eine Fehlermeldung", e.getMessage());
    }
  }

  public static class DemoClass {
    public void doSomething() {
      //throw new RuntimeException("Eine Fehlermeldung");
    }
  }
}
