package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import static org.junit.jupiter.api.Assertions.*;

public class C0706Test {

  @ExtendWith(DataHolderParameterResolver.class)
  public interface BaseInterface {

    @BeforeEach
    default void setUp(DataHolder dataHolder) {
      System.out.println("dataHolder = " + dataHolder);
      assertNotNull(dataHolder);
    }

    @Test
    default void test001(DataHolder dataHolder) {
      assertTrue(dataHolder.age() > 0);
    }
  }

  public static class TestClass implements BaseInterface {
    @Test
    void test002(DataHolder dataHolder) {
      assertEquals("A", dataHolder.name());
    }
  }

  public record DataHolder(String name, int age) {
  }

  public static class DataHolderParameterResolver
      implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
      return parameterContext.getParameter().getType() == DataHolder.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
      return new DataHolder("A", 1);
    }
  }
}
