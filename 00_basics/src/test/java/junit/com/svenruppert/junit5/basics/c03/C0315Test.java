package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class C0315Test {

  @Test
  void test001() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      throw new NullPointerException("Das ging schief...");
    });
  }

  @Disabled
  @Test
  void test002() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      throw new RuntimeException("Das ging schief...");
    });
  }

  @Disabled
  @Test
  void test003() {
    Assertions.assertDoesNotThrow(()->{
      throw new NullPointerException("UUUPS...");
    }, "Das ging schief...");
  }
}
