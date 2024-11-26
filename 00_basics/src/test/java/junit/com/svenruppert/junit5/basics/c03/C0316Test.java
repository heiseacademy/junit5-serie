package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class C0316Test {

  @Test
  void test001() {
    Assertions.assertThrowsExactly(RuntimeException.class, () -> {
      throw new NullPointerException("Das ging schief...");
    });
  }

  @Test
  void test002() {
    Assertions.assertThrowsExactly(RuntimeException.class, () -> {
      throw new RuntimeException("Das ging schief...");
    });
  }
}
