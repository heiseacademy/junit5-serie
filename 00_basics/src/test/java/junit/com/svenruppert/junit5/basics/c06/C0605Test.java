package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
class C0605Test {
  @ParameterizedTest
  @EnumSource(value = DemoEnum.class)
  void test001(DemoEnum enumValue) {
    assertTrue(enumValue == DemoEnum.A);
  }

  @ParameterizedTest
  @EnumSource(value = DemoEnum.class, names = {"A", "C"})
  void test002(DemoEnum enumValue) {
    assertTrue(enumValue == DemoEnum.A);
  }

  @ParameterizedTest
  @EnumSource(
      value = DemoEnum.class,
      names = {"A", "C"},
      mode = EnumSource.Mode.EXCLUDE)
  void test003(DemoEnum enumValue) {
    assertTrue(enumValue == DemoEnum.A);
  }

  public enum DemoEnum {A, B, C}
}
