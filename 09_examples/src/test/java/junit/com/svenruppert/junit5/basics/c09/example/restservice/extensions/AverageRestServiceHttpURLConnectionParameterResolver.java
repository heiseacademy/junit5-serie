package junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class AverageRestServiceHttpURLConnectionParameterResolver
    extends AbstractHttpURLConnectionParameterResolver
    implements
    AfterEachCallback,
    HasLogger {

  public static final String HTTP_URL_CONNECTION_STORE_KEY = "httpConnection";

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getType() == HttpURLConnection.class;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    HttpURLConnection httpConnection = createHttpConnection(parameterContext, extensionContext);
    return httpConnection;
  }


  @Override
  public String getNameSpaceKey() {
    return HTTP_URL_CONNECTION_STORE_KEY;
  }

  protected void initHttpConnection(HttpURLConnection httpConnection) throws ProtocolException {
    logger().info("Initializing http connection");
    httpConnection.setRequestMethod("POST");
    httpConnection.setDoOutput(true);
    httpConnection.setRequestProperty("Content-Type", "text/plain");
  }

}
