package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class C0611Test {


  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3})
  @ValueSource(ints = {6,7,8})
  void test001(int i) {
    System.out.println(i);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3})
  @CsvSource({"1","2","3"})
  void test002(int i) {
    System.out.println(i);
  }
}
