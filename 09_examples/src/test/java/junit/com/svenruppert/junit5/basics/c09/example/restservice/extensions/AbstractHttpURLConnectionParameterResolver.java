package junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;

public abstract class AbstractHttpURLConnectionParameterResolver
    implements
    ParameterResolver,
    AfterEachCallback,
    HasLogger {

  public abstract String getNameSpaceKey();

  protected abstract void initHttpConnection(HttpURLConnection httpConnection) throws ProtocolException;


  public ExtensionContext.Namespace createHttpURLConnectionNameSpace(ExtensionContext context) {
    Method requiredTestMethod = context.getRequiredTestMethod();
    ExtensionContext.Namespace namespace = ExtensionContext.Namespace.create(requiredTestMethod.getName());
    logger().info("Creating namespace for {}", requiredTestMethod.getName());
    return namespace;
  }

  public HttpURLConnection createHttpConnection(ParameterContext parameterContext,
                                                ExtensionContext extensionContext)
      throws ParameterResolutionException {
    var annotation = parameterContext.getAnnotatedElement().getAnnotation(Http.class);
    if (annotation == null) {
      throw new ParameterResolutionException("No @Http annotation found");
    }
    String target = annotation.target();
    String url = "http://" + target + ":" + annotation.port() + annotation.path();
    logger().info("Using target: {}", url);
    try {
      URI uri = URI.create(url);
      HttpURLConnection httpConnection = (HttpURLConnection) uri.toURL().openConnection();
      initHttpConnection(httpConnection);
      ExtensionContext.Namespace nameSpace = createHttpURLConnectionNameSpace(extensionContext);
      ExtensionContext.Store store = extensionContext.getStore(nameSpace);
      logger().info("Using store: {}", store);
      String nameSpaceKey = getNameSpaceKey();
      logger().info("Using namespace key: {}", nameSpaceKey);
      store.put(nameSpaceKey, httpConnection);
      return httpConnection;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    ExtensionContext.Store store = context.getStore(createHttpURLConnectionNameSpace(context));
    HttpURLConnection httpURLConnection = store.get(getNameSpaceKey(), HttpURLConnection.class);
    if (httpURLConnection != null) {
      logger().info("Closing connection: {}", httpURLConnection);
      httpURLConnection.disconnect();
      store.remove(getNameSpaceKey());
      logger().info("Removed connection");
    } else {
      logger().info("afterEach - No httpConnection in Store found..");
    }
  }

}
