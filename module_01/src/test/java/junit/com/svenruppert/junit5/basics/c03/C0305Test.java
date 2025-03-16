package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class C0305Test {


  @Test
  void test001() {
    int a = 5;
    boolean condition = a > 2;
    Assertions.assertTrue(condition);
  }

  @Disabled
  @Test
  void test002() {
    int a = 5;
    boolean condition = a < 2;
    String message = "Der Wert ist nicht richtig";
    //Assertions.assertTrue(condition, message);
    Supplier<String> msg = () -> {
      String result = "Der Wert ist nicht richtig " + LocalDateTime.now().toString();
      return result;
    };

    Assertions.assertTrue(condition, msg);

  }


  @Test
  void test003() {
    int a = 5;
    boolean condition = a < 2;
    Assertions.assertFalse(condition);


  }
}
