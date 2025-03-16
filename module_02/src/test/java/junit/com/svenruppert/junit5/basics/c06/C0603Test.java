package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class C0603Test {
//06.03 - @CsvSource , @CsvFileSource


  @Disabled
  @ParameterizedTest
  @CsvSource(value = {" 1,3", "3,1 ", "5,4"},
      ignoreLeadingAndTrailingWhitespace = false
  )
  void test001(int a, int b) {
    assertTrue(a > b);
  }

  @Disabled
  @ParameterizedTest
  @CsvFileSource(files = "src/test/resources/C0603Test_CSV_Data.csv")
  void test002(int a, int b) {
    assertTrue(a > b);
  }
}
