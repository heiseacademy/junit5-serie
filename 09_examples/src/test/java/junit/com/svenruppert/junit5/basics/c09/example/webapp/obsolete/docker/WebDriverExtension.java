package junit.com.svenruppert.junit5.basics.c09.example.webapp.obsolete.docker;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.GenericContainer;

import java.net.URI;
import java.net.URL;

public class WebDriverExtension
    implements
    BeforeEachCallback, AfterEachCallback,
    ParameterResolver {

  private static final String STORE_KEY = "WEB_DRIVER";

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    // Selenoid-Container-Infos abrufen
    ExtensionContext.Store globalStore = context.getStore(ExtensionContext.Namespace.GLOBAL);
    GenericContainer<?> selenoid = globalStore.get("SELENOID_CONTAINER", GenericContainer.class);
//
    if (selenoid == null) {
      throw new IllegalStateException("Selenoid container not available!");
    }

    String remoteUrl = "http://" + selenoid.getHost() + ":" + selenoid.getMappedPort(4444) + "/wd/hub";
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("chrome");

    URL remoteAddress = new URI(remoteUrl).toURL();
    WebDriver driver = new RemoteWebDriver(remoteAddress, capabilities);

    // WebDriver im Test-spezifischen Store ablegen
    context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestMethod()))
        .put(STORE_KEY, driver);
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    WebDriver driver = getWebDriver(context);
    if (driver != null) {
      driver.quit();
    }
  }

  private WebDriver getWebDriver(ExtensionContext context) {
    return context.getStore(ExtensionContext.Namespace.create(context.getRequiredTestMethod()))
        .get(STORE_KEY, WebDriver.class);
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.getParameter().getType().equals(WebDriver.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return getWebDriver(extensionContext);
  }
}
