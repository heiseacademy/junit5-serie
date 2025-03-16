package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class C0602Test {

  @Disabled
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3})
  void test001(int a) {
    assertEquals(2, a);
  }
}
