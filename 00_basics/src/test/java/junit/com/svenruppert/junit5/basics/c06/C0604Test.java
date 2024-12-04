package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class C0604Test {

  //06.04 - @EmptySource , @NullSource , @NullAndEmptySource


  @ParameterizedTest
  @EmptySource()
  void test001(List<String> values) {
    assertTrue(values.isEmpty());
  }

  @ParameterizedTest
  @NullSource()
  void test002(List<String> values) {
    assertTrue(values == null);
  }


  @ParameterizedTest
  @NullAndEmptySource()
  void test003(List<String> values) {
    assertTrue(values == null || values.isEmpty());
  }
}
