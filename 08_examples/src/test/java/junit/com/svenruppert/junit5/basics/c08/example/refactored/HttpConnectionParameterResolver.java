package junit.com.svenruppert.junit5.basics.c08.example.refactored;

import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;

import static junit.com.svenruppert.junit5.basics.c08.example.refactored.HttpConnectionExtension.createNameSpace;

public class HttpConnectionParameterResolver
    implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext
        .getParameter()
        .getType()
        .equals(HttpURLConnection.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
                                 ExtensionContext extensionContext)
      throws ParameterResolutionException {

    ExtensionContext.Store store = extensionContext.getStore(createNameSpace(extensionContext));

    return store.get(HttpConnectionExtension.STORE_KEY, HttpURLConnection.class);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({
      ElementType.PARAMETER,
      ElementType.METHOD,
      ElementType.TYPE,
      ElementType.ANNOTATION_TYPE})
  @ExtendWith(HttpConnectionParameterResolver.class)
  public @interface HttpConnection {
  }
}
