package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class C0608Test {

  @Disabled
  @ParameterizedTest
  @ArgumentsSource(DemoArgumentProvider.class)
  void test001(String input) {
    assertEquals("C", input);
  }

  public static class DemoArgumentProvider
      implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
        throws Exception {
      return Stream.of(
          Arguments.of("A"),
          Arguments.of("B"),
          Arguments.of("C"),
          Arguments.of("D")
      );
    }
  }

}
