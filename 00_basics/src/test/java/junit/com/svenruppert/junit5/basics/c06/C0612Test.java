package junit.com.svenruppert.junit5.basics.c06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class C0612Test {

  public record Person(String name, int age) {}

  public record ValueHolder(Person person, String value) {}

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.PARAMETER)
  @AggregateWith(CsvToValueHolderAggregator.class)
  public @interface CsvToValueHolder{}

  public static class CsvToValueHolderAggregator
      implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor,
                                     ParameterContext context)
        throws ArgumentsAggregationException {
      if(accessor.size() != 3)
        throw new ArgumentsAggregationException("falsche Anzahl Argumente..");
      String name = accessor.getString(0);
      int age = accessor.getInteger(1);
      String value = accessor.getString(2);
      return new ValueHolder(new Person(name, age),value );
    }
  }

  @ParameterizedTest
  @CsvSource({"A,1,a", "B,2,a", "C,3,x"})
  void test001(@AggregateWith(CsvToValueHolderAggregator.class) ValueHolder valueHolder) {
    assertNotNull(valueHolder.person);
    assertNotNull(valueHolder.value);
  }

  @ParameterizedTest
  @CsvSource({"A,1,a", "B,2,a", "C,3,x"})
  void test002(@CsvToValueHolder ValueHolder valueHolder) {
    assertNotNull(valueHolder);
  }

  @ParameterizedTest
  @CsvSource({"A,1,a", "B,2,a", "C,3,x"})
  void test003(ArgumentsAccessor accessor) {
    Person person = convertToPerson(accessor);

    String value = accessor.getString(2);

    assertNotNull(person);
    assertNotNull(value);

  }

  private static Person convertToPerson(ArgumentsAccessor accessor) {
    String name = accessor.getString(0);
    int age = accessor.getInteger(1);
    Person person = new Person(name,age);
    return person;
  }
}
