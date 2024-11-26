package junit.com.svenruppert.junit5.basics.c04;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class C0403Test {

  @Test
  void test001() {
    assumeTrue(false, "This is a test");
    //System.out.println("Hello World!");
    //fail();
  }
}
