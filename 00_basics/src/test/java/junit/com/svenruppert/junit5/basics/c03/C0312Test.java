package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class C0312Test {


  @Test
  void test001() {
    Stream<String> line1 = List.of("a", "b", "c").stream();
    Stream<String> line2 = List.of("a", "ba", "c").stream();

    Assertions.assertLinesMatch(line1, line2);
  }
}
