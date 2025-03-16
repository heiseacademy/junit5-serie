package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class C0609Test {

  @Test
  @ExtendWith(PersonResolver.class)
  void test001(Person person) {
    assertNotNull(person);
    assertEquals("Name", person.getName());
  }

  public static class PersonResolver
      implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
        throws ParameterResolutionException {
      Parameter parameter = parameterContext.getParameter();
      Class<?> type = parameter.getType();
      return type.equals(Person.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
      return new Person("Name", 5);
    }
  }

  public static class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }
  }
}
