package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;

//@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayNameGeneration(C0613Test.MyDisplayNameGenerator.class)
public class C0613Test {

  public static class MyDisplayNameGenerator
      extends DisplayNameGenerator.Standard{
    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
      return testClass.getSimpleName() + " - XX ";
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
      return nestedClass.getSimpleName() + " - Nested XX ";
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
      return testMethod.getName().toUpperCase();
    }
  }


  @Test
  void ein_Name_mit_Infos() {}

  @Test @DisplayName("Meine sinnvolle Beschreibung")
  void xx() {}


  @DisplayName("Eine Menge Werte...")
  @ParameterizedTest(name = "Test Nr. {index} mit dem Wert {0}")
  @ValueSource(ints = {0, 1, 2, 3, 4, 5})
  void test002(int number) {
  }

  @Test
  void test_001() { }
}
