package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class C0307Test {


  public static class Person {
    int age;
    String name;

    public Person(int age, String name) {
      this.age = age;
      this.name = name;
    }

  }

  @Disabled
  @Test
  void test001() {
    Person hugo = new Person(5, "Hugo");
    Assertions.assertNotNull(hugo);
    Assertions.assertNull(hugo, "Wert ist nicht korrekt..");

  }
}
