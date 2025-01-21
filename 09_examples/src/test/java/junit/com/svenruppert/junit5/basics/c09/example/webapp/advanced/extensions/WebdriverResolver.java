package junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

import static junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced.extensions.WebdriverResolver.WebBrowser.CHROME;


public class WebdriverResolver
    implements
    BeforeAllCallback, AfterAllCallback,
    BeforeEachCallback, AfterEachCallback,
    ParameterResolver {

  public static final String STORE_KEY_WEB_DRIVER_MANAGER = "WEB_DRIVER_MANAGER";
  public static final String STORE_KEY_WEB_DRIVER = "WEB_DRIVER";

  public static Namespace webDriverNameSpace(ExtensionContext extensionContext) {
    return Namespace.create(extensionContext.getRequiredTestMethod().getName());
  }

  public static Namespace webDriverManagerNameSpace(ExtensionContext extensionContext) {
    return Namespace.create(extensionContext.getRequiredTestClass().getName());
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType().equals(WebDriver.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
                                 ExtensionContext extensionContext) throws ParameterResolutionException {
    Optional<Object> testInstance = parameterContext.getTarget();

    if (testInstance.isPresent()) {
      ExtensionContext.Store contextStore = extensionContext.getStore(webDriverNameSpace(extensionContext));
      return contextStore.get(STORE_KEY_WEB_DRIVER, WebDriver.class);
    } else {
      throw new AssertionError("No parameterContext.getTarget() found");
    }
  }


  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    ExtensionContext.Store contextStore = context.getStore(webDriverNameSpace(context));
    WebDriver webDriver = contextStore.get(STORE_KEY_WEB_DRIVER, WebDriver.class);
    webDriver.quit();
    contextStore.remove(STORE_KEY_WEB_DRIVER, WebDriver.class);
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    Object testInstance = context.getRequiredTestInstance();
    UseWebDriver annotation = testInstance.getClass().getAnnotation(UseWebDriver.class);
    if (annotation == null) {
      throw new AssertionError("No @UseWebDriver annotation found");
    }
    WebBrowser webBrowser = annotation.value();
    RemoteWebDriver webDriver = switch (webBrowser) {
      case CHROME -> new ChromeDriver();
      case FIREFOX -> new FirefoxDriver();
      case SAFARI -> new SafariDriver();
    };
    ExtensionContext.Store contextStore = context.getStore(webDriverNameSpace(context));
    contextStore.put(STORE_KEY_WEB_DRIVER, webDriver);

  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    ExtensionContext.Store contextStore = context.getStore(webDriverManagerNameSpace(context));
    WebDriverManager webDriverManager = contextStore.get(STORE_KEY_WEB_DRIVER_MANAGER, WebDriverManager.class);
    if (webDriverManager != null) webDriverManager.quit();
    contextStore.remove(STORE_KEY_WEB_DRIVER_MANAGER, WebDriverManager.class);
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    ExtensionContext.Store contextStore = context.getStore(webDriverManagerNameSpace(context));
    Class<?> testClass = context.getRequiredTestClass();
    UseWebDriver annotation = testClass.getAnnotation(UseWebDriver.class);
    if (annotation == null) {
      throw new AssertionError("No @UseWebDriver annotation found");
    }
    WebBrowser value = annotation.value();
    WebDriverManager webDriverManager = switch (value) {
      case WebBrowser.CHROME -> WebDriverManager.chromedriver();
      case WebBrowser.FIREFOX -> WebDriverManager.firefoxdriver();
      case WebBrowser.SAFARI -> WebDriverManager.safaridriver();
    };
    webDriverManager.setup();
    contextStore.put(STORE_KEY_WEB_DRIVER_MANAGER, webDriverManager);
  }

  public enum WebBrowser {
    CHROME, FIREFOX, SAFARI;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE})
  @ExtendWith(WebdriverResolver.class)
  public @interface UseWebDriver {
    WebBrowser value() default CHROME;
  }
}
