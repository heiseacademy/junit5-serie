package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Assertions - assertInstanceOf
public class C0309Test {

  @Test
  void test001() {
    String str = "Hello World";
    Assertions.assertInstanceOf(String.class, str);
  }

  public interface MarkerInterface{}
  public static class DemoClass
      implements MarkerInterface{ }

  public static class DemoClass2
  extends DemoClass{}

  @Test
  void test002() {
    Assertions.assertInstanceOf(DemoClass.class, new DemoClass());
    Assertions.assertInstanceOf(MarkerInterface.class, new DemoClass());
    Assertions.assertInstanceOf(DemoClass2.class, new DemoClass());


  }


}
