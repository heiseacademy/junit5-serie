package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

//03.10 - Assertions - assertArrayEquals
public class C0310Test {

  @Test
  void test001() {
    int[] aPrimitive = {1, 2, 3, 4, 5};
    int[] bPrimitive = {1, 2, 3, 4, 5};
    Assertions.assertArrayEquals(aPrimitive, bPrimitive);
  }

  @Test
  void test002() {
    Integer[] aInteger = {1, 2, 3, 4, 5};
    Integer[] bInteger = {1, 2, 3, 4, 5};
    Assertions.assertArrayEquals(aInteger, bInteger);
  }

  @Test
  void test003() {
    Person[] aPerson = {
        new Person("a", 1),
        new Person("b", 2),
        new Person("c", 3)};
    Person[] bPerson = {
        new Person("a", 1),
        new Person("b", 2),
        new Person("c", 3)};

    Assertions.assertArrayEquals(aPerson, bPerson);

  }

  public static class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      Person person = (Person) o;
      return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, age);
    }
  }


}
