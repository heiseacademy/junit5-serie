package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class C0710Test {

  @TestFactory
  public Stream<DynamicTest> generateDynamicTests() {
    return IntStream
        .range(-1, 10)
        .mapToObj(i -> DynamicTest.dynamicTest("Test, ob " + i + " positiv ist ",
            () -> {
              assertTrue(i > 0);
            }));
  }
}
