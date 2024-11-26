package junit.com.svenruppert.junit5.basics.c04;

import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assumptions.*;
public class C0404Test {

  @Test
  void test001() {
    BooleanSupplier supplier = () -> false;

    assumeFalse(supplier, "Message");
    System.out.println("Hello World");
  }
}
