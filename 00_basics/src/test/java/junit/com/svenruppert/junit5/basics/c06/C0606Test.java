package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class C0606Test {


  public static Stream<String> valueMethod() {
    return Stream.of("A", "B", "C");
  }

  @ParameterizedTest
  @MethodSource(value="valueMethod")
  void test001(String value) {
    assertTrue(value.length() > 0);
  }
}
