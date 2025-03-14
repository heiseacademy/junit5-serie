package junit.com.svenruppert.junit5.basics.c04;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class C0403Test {

  @Test
  @Disabled
  void test001() {
    assumeTrue(false, "This is a test");
    //System.out.println("Hello World!");
    //fail();
  }
}
