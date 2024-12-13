package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

public class C0711Test {


public record DataInfo(LocalDateTime datTime, int step){}

public static class DataInfoParameterResolver
    implements ParameterResolver {

  private DataInfo dataInfo;

  public DataInfoParameterResolver(DataInfo dataInfo) {
    this.dataInfo = dataInfo;
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getType().isAssignableFrom(DataInfo.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
                                 ExtensionContext extensionContext)
      throws ParameterResolutionException {

    return dataInfo;
  }
}


public static class DataInfoInvocationContext
    implements TestTemplateInvocationContext{

  private final DataInfo dataInfo;

  public DataInfoInvocationContext(DataInfo dataInfo) {
    this.dataInfo = dataInfo;
  }

  @Override
  public String getDisplayName(int invocationIndex) {
     return " -> [" + invocationIndex + "]";
  }

  @Override
  public List<Extension> getAdditionalExtensions() {
    DataInfoParameterResolver resolver
        = new DataInfoParameterResolver(dataInfo);

    return Collections.singletonList(resolver);
  }
}


public static class DataInfoInvocationContextProvider
    implements TestTemplateInvocationContextProvider {

  @Override
  public boolean supportsTestTemplate(ExtensionContext context) {
    if(isAnnotated(context.getTestMethod(), DataTest.class)){
      DataTest annotation = context.getRequiredTestMethod().getAnnotation(DataTest.class);
      String value = annotation.value();
      return true;
    } else return false;
  }

  @Override
  public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
    return IntStream
        .range(0, 5) // get data, create holder
        .mapToObj( step -> new DataInfo(LocalDateTime.now(), step))
        .map(DataInfoInvocationContext::new);
  }
}

  @Retention(RUNTIME) @Target(METHOD) @TestTemplate
  public @interface DataTest{
    String value() default "Super";
  }


  @ExtendWith(DataInfoInvocationContextProvider.class)
  public static class TestClass {

    @DataTest("Hallo")
    //@ExtendWith(DataInfoInvocationContextProvider.class)
    void test001(DataInfo dataInfo) {
      System.out.println("dataInfo = " + dataInfo);
      assertTrue(dataInfo.step >= 0);
    }
  }

}
