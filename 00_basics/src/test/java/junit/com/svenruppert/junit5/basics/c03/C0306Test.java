package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class C0306Test {

  @Test
  void test001() {

    //assertEquals(1,2,"Die Werte sind  nicht gleich");
    //assertNotEquals(1,2,"Die Werte sind  nicht gleich");

    //assertEquals("Hallo", "Hallo");

    //assertEquals(1.0, 1.01, 0.001);

  }

  public static class Demo{
    private int a;
    private int b;

    public Demo(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Demo demo = (Demo) o;
      return a == demo.a && b == demo.b;
    }

    @Override
    public int hashCode() {
      return Objects.hash(a, b);
    }
  }


  @Test
  void test002() {

    Demo a = new Demo(1, 2);
    Demo b = new Demo(1, 2);

    assertEquals(a,b);

  }
}
