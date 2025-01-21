package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

public class C0711Test_Extended {

  @Retention(RUNTIME)
  @Target(METHOD)
  @TestTemplate
  public @interface DataMatrixTest {
  }

  public record DataInfoA(LocalDateTime dateTime, int step) {
  }

  public record DataInfoB(LocalDateTime dateTime, int step) {
  }

  public static class DataInfoAParameterResolver implements ParameterResolver {
    private final DataInfoA dataInfo;

    public DataInfoAParameterResolver(DataInfoA dataInfo) {
      this.dataInfo = dataInfo;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
      return parameterContext.getParameter().getType() == DataInfoA.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
      return dataInfo;
    }
  }

  public static class DataInfoBParameterResolver implements ParameterResolver {
    private final DataInfoB dataInfo;

    public DataInfoBParameterResolver(DataInfoB dataInfo) {
      this.dataInfo = dataInfo;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
      return parameterContext.getParameter().getType() == DataInfoB.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
      return dataInfo;
    }
  }

  public static class DataMatrixInvocationContext implements TestTemplateInvocationContext {
    private final DataInfoA dataInfoA;
    private final DataInfoB dataInfoB;

    public DataMatrixInvocationContext(DataInfoA dataInfoA, DataInfoB dataInfoB) {
      this.dataInfoA = dataInfoA;
      this.dataInfoB = dataInfoB;
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
      DataInfoAParameterResolver dataInfoAParameterResolver = new DataInfoAParameterResolver(dataInfoA);
      DataInfoBParameterResolver dataInfoBParameterResolver = new DataInfoBParameterResolver(dataInfoB);
      return List.of(dataInfoAParameterResolver, dataInfoBParameterResolver);
    }
  }

  public static class DataInfoMatrixInvocationContextProvider
      implements TestTemplateInvocationContextProvider {


    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
      return isAnnotated(context.getTestMethod(), DataMatrixTest.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {

      List<DataInfoA> dataInfoAList = IntStream.range(0, 3).mapToObj(step -> new DataInfoA(LocalDateTime.now(), step)).toList();
      List<DataInfoB> dataInfoBList = IntStream.range(0, 5).mapToObj(step -> new DataInfoB(LocalDateTime.now(), step)).toList();
      return dataInfoAList
          .stream()
          .flatMap(dataInfoA -> dataInfoBList.stream()
              .map(dataInfoB -> new DataMatrixInvocationContext(dataInfoA, dataInfoB)));
    }
  }

  @ExtendWith(DataInfoMatrixInvocationContextProvider.class)
  public static class TestClass {

    @DataMatrixTest
    void test001(DataInfoA dataInfoA, DataInfoB dataInfoB) {
      System.out.println("dataInfoA = " + dataInfoA);
      System.out.println("dataInfoB = " + dataInfoB);
      assertTrue(dataInfoA.step() >= 0);
      assertTrue(dataInfoB.step() >= 0);

    }
  }


}
