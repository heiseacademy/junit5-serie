package junit.com.svenruppert.junit5.basics.c03;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

// assertNotSame
public class C0308Test {

  public static class Person{
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


  @Test
  void test001() {
    String a = "abc";
    String b = "abc";
    assertEquals(a, b);
    assertSame(a,b);
  }

  @Test
  void test002() {
    Person a = new Person("A", 5);
    Map<String, Person> map = new HashMap<>();
    map.put("A", a);
    //Assertions.assertEquals(a, b);
    assertSame(a,map.get("A"));

    Integer x = Integer.valueOf(5);
    Integer y = Integer.valueOf(5);
   assertSame(x, y);

  }
}


