package junit.com.svenruppert.junit5.basics.c09.example.webapp.obsolete.docker;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

public class SelenoidExtension
    implements BeforeAllCallback, AfterAllCallback {

  private static final String STORE_KEY = "SELENOID_CONTAINER";

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    GenericContainer<?> selenoid = new GenericContainer<>("aerokube/selenoid:latest-release")
        .withExposedPorts(4444)
        .withEnv("OVERRIDE_VIDEO_OUTPUT_DIR", "/tmp/video")
        .withEnv("TZ", "UTC");
    selenoid.start();
//
//     Selenoid-Container im Store hinterlegen
    context.getStore(ExtensionContext.Namespace.GLOBAL).put(STORE_KEY, selenoid);
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    GenericContainer<?> selenoid = context.getStore(ExtensionContext.Namespace.GLOBAL)
        .get(STORE_KEY, GenericContainer.class);
    if (selenoid != null && selenoid.isRunning()) {
      selenoid.stop();
    }
  }
}
