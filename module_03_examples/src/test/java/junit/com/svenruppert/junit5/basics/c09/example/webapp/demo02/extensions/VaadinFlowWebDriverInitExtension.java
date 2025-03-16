package junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions;

import com.svenruppert.dependencies.core.logger.HasLogger;
import junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.VaadinFlowAppExtension.VaadinFlowTest;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.WebdriverResolver.STORE_KEY_WEB_DRIVER;
import static junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.WebdriverResolver.webDriverNameSpace;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class VaadinFlowWebDriverInitExtension
    implements BeforeEachCallback, HasLogger {


  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    Class<?> testClass = extensionContext.getRequiredTestClass();
    VaadinFlowTest annotation = testClass.getAnnotation(VaadinFlowTest.class);
    if (annotation == null) {
      throw new AssertionError("No @VaadinFlowTest annotation found");
    }

    ExtensionContext.Store contextStore = extensionContext.getStore(webDriverNameSpace(extensionContext));
    WebDriver webDriver = contextStore.get(STORE_KEY_WEB_DRIVER, WebDriver.class);

    webDriver.manage().window().maximize();
    String URL = annotation.target() + ":" + annotation.port();
    webDriver.get(URL);
    new WebDriverWait(webDriver, ofSeconds(10), ofSeconds(1))
        .until(visibilityOfElementLocated(By.id(annotation.elementID())));
  }
}
