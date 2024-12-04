package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class C0610Test {
//AnnotationBasedArgumentConverter

  @ParameterizedTest
  @ValueSource(strings = {"A,1", "B,2", "C,3"})
  void test001(@ConvertWith(PersonConverter.class) Person person) {
    assertNotNull(person);
    assertFalse(person.name.isEmpty());
    assertTrue(person.age > 0);
  }

  @ParameterizedTest
  @ValueSource(strings = {"A,1", "B,2", "C,3"})
  void test001A(@StringToPerson Person person) {
    assertNotNull(person);
    assertFalse(person.name.isEmpty());
    assertTrue(person.age > 0);
  }

  public record Person(int age, String name) {
  }

  @Target(ElementType.PARAMETER)
  @Retention(RetentionPolicy.RUNTIME)
  @ConvertWith(PersonAnnotationConverter.class)
  public @interface StringToPerson{}

  public static class PersonAnnotationConverter
    extends AnnotationBasedArgumentConverter<StringToPerson>{
    @Override
    protected Object convert(Object source,
                             Class<?> targetType,
                             StringToPerson annotation) throws ArgumentConversionException {
      return convertPerson(source);
    }
  }

  public static class PersonConverter
      implements ArgumentConverter {
    @Override
    public Object convert(Object source,
                          ParameterContext context)
        throws ArgumentConversionException {
      return convertPerson(source);
    }


  }
  private static Person convertPerson(Object source) {
    if(!(source instanceof String)) {
      throw new ArgumentConversionException("falscher InputType");
    }
    String[] parts = ((String) source).split(",");
    if(parts.length != 2) {
      throw new ArgumentConversionException("falscher Formatierung");
    }
    int age = Integer.parseInt(parts[1]);
    String name = parts[0];
    return new Person(age, name);
  }

  @ParameterizedTest
  @ValueSource(strings={"01.01.2019"})
  void test002(@JavaTimeConversionPattern("dd.MM.yyyy")LocalDate localDate) {
    assertEquals(2019, localDate.getYear());
  }
}
