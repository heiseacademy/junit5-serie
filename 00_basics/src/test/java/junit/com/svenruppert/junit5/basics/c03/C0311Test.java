package junit.com.svenruppert.junit5.basics.c03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class C0311Test {


  @Test
  void test001() {

    List<String> values = List.of("a", "b", "c", "d", "e", "f", "g");
        //.reversed();

    final Set<String> setHash = new HashSet<>(values);
    final Set<String> setTree = new TreeSet<>(values);

    System.out.println("values = " + values);
    System.out.println("setHash = " + setHash);
    System.out.println("setTree = " + setTree);

    Assertions.assertIterableEquals(values, values);
    //Assertions.assertIterableEquals(values, setTree);
    Assertions.assertIterableEquals(values, setHash);
  }

  public static class Person
      implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age, String name) {
      this.age = age;
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Person person = (Person) o;
      return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(age, name);
    }

    @Override
    public int compareTo(Person other) {
      // Zuerst nach Alter sortieren
      int ageComparison = Integer.compare(this.age, other.age);
      if (ageComparison != 0) {
        return ageComparison;
      }
      // Wenn das Alter gleich ist, nach Name sortieren
      return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
      return "Person{" +
          "age=" + age +
          ", name='" + name + '\'' +
          '}';
    }
  }


  @Test
  void test002() {
    List<Person> values = List.of(
            new Person(1, "a"),
            new Person(2, "b"),
            new Person(3, "c"),
            new Person(4, "d"))
        .reversed();

    final Set<Person> setHash = new HashSet<>(values);
    final Set<Person> setTree = new TreeSet<>(values);

    final Map<Integer, Person> map = new HashMap<>();
    values.forEach(person -> map.put(person.age, person));

    System.out.println("values = " + values);
    System.out.println("setHash = " + setHash);
    System.out.println("setTree = " + setTree);
    System.out.println("map = " + map.keySet());

    Assertions.assertIterableEquals(values, values);
    Assertions.assertIterableEquals(values, setHash);

    Assertions.assertIterableEquals(values, setTree);


  }
}
