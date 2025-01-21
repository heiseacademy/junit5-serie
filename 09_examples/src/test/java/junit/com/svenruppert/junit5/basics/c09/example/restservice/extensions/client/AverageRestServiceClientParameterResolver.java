package junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.client;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c09.example.restservice.client.AverageRestServiceClient;
import com.svenruppert.junit5.basics.c09.example.restservice.server.AverageRestServer;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.AbstractHttpURLConnectionParameterResolver;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.server.AverageRestServiceLifeCycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class AverageRestServiceClientParameterResolver
    extends AbstractHttpURLConnectionParameterResolver
    implements HasLogger {


  public static final String AVERAGE_REST_SERVICE_CLIENT_STORE_KEY = "AverageRestServiceClient";

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getType() == AverageRestServiceClient.class;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
                                 ExtensionContext extensionContext)
      throws ParameterResolutionException {

    HttpURLConnection httpConnection = createHttpConnection(parameterContext, extensionContext);
    return new AverageRestServiceClient(httpConnection);
  }

  @Override
  public String getNameSpaceKey() {
    return AVERAGE_REST_SERVICE_CLIENT_STORE_KEY;
  }

  @Override
  protected void initHttpConnection(HttpURLConnection httpConnection) throws ProtocolException {
    //nothing special to do
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @ExtendWith(AverageRestServiceLifeCycle.class)
  @ExtendWith(AverageRestServiceClientParameterResolver.class)
  public @interface AverageRestServiceClientLifeCycle {
    String target() default "localhost";

    int port() default 8080;

    String path() default AverageRestServer.PATH;
  }


}
