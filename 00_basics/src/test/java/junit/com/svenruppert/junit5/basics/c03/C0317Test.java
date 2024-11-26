package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class C0317Test {

  @Test
  void test001() {

    Assertions.assertAll("Header...",
        () -> {
          assertTrue(true, "01");
        },
        () -> {
          assertTrue(false, "02");
        },
        () -> {
          assertThrows(NullPointerException.class, () -> {
            throw new RuntimeException("Das ging schief..");
          }, "03");
        },
        () -> {
          //throw new RuntimeException("Das ging schief.. Teil II");
        }
    );
  }
}
