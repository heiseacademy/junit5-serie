package junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.server;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c09.example.restservice.server.AverageRestServer;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import java.io.IOException;
import java.util.Random;

public class AverageRestServiceLifeCycle
    implements
    BeforeAllCallback, AfterAllCallback,
    HasLogger {

  public static final int minPort = 8080;
  public static final int maxPort = 9090;
  public static Namespace NAMESPACE = Namespace.create(AverageRestServiceLifeCycle.class);

  public int getRandomPort(int min, int max) {
    Random random = new Random();
    return random.nextInt((max - min) + 1) + min;
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    ExtensionContext.Store store = context.getStore(NAMESPACE);
    AverageRestServer service = store.get(AverageRestServiceLifeCycle.class,
        AverageRestServer.class);
    logger().info("Average REST Service - shutting down at port {}", service.getPort());
    service.stopServer();
    store.remove(AverageRestServiceLifeCycle.class);
    logger().info("removed Average REST Service service ");
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    Class<?> requiredTestClass = context.getRequiredTestClass();
    if (requiredTestClass == null) {
      throw new AssertionError("Required test class not found");
    }

    AverageRestService annotation = requiredTestClass.getAnnotation(AverageRestService.class);
    int port = annotation.port();
    boolean notInRange = port < minPort || port > maxPort;
    int servicePort = notInRange ? getRandomPort(minPort, maxPort):port;

    ExtensionContext.Store store = context.getStore(NAMESPACE);
    AverageRestServer averageRestServer = new AverageRestServer();
    logger().info("Average REST service port: {}", servicePort);
    try {
      averageRestServer.startServer(servicePort);
      logger().info("Average REST service started at port {}", servicePort);
      store.put(AverageRestServiceLifeCycle.class, averageRestServer);
      logger().info("Average REST service stored in context");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}
