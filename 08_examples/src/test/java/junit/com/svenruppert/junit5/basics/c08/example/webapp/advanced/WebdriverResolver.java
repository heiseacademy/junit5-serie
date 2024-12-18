package junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

import static junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced.WebdriverResolver.WebBrowser.CHROME;


public class WebdriverResolver
    implements
    BeforeAllCallback, AfterAllCallback,
    BeforeEachCallback, AfterEachCallback,
    ParameterResolver {

  public static final String STORE_KEY_WEB_DRIVER_MANAGER = "WEB_DRIVER_MANAGER";
  public static final String STORE_KEY_WEB_DRIVER = "WEB_DRIVER";

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
      ExtensionContext.Store contextStore = extensionContext.getStore(Namespace.create(extensionContext.getRequiredTestMethod()));
      return contextStore.get(STORE_KEY_WEB_DRIVER, WebDriver.class);
    } else {
      throw new AssertionError("No WebDriver found");
    }
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    ExtensionContext.Store contextStore = context.getStore(Namespace.create(context.getRequiredTestMethod()));
    WebDriver webDriver = contextStore.get(STORE_KEY_WEB_DRIVER, WebDriver.class);
    webDriver.quit();
    contextStore.remove(STORE_KEY_WEB_DRIVER, WebDriver.class);
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
//    driver = new FirefoxDriver();
//    selector = new MainViewSeleniumTest.FlowSelectorByID(driver);
    Optional<Object> testInstance = context.getTestInstance();

    if (testInstance.isPresent()) {
      WebBrowser webBrowser = testInstance.get().getClass().getAnnotation(UseWebDriver.class).value();
      RemoteWebDriver webDriver = webBrowser.equals(CHROME) ? new ChromeDriver() : new FirefoxDriver();
      ExtensionContext.Store contextStore = context.getStore(Namespace.create(context.getRequiredTestMethod()));
      contextStore.put(STORE_KEY_WEB_DRIVER, webDriver);
    } else {
      throw new AssertionError("No WebDriver found");
    }
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    ExtensionContext.Store contextStore = context.getStore(Namespace.create(context.getRequiredTestClass()));
    WebDriverManager webDriverManager = contextStore.get(STORE_KEY_WEB_DRIVER_MANAGER, WebDriverManager.class);
    if (webDriverManager != null) webDriverManager.quit();
    contextStore.remove(STORE_KEY_WEB_DRIVER_MANAGER, WebDriverManager.class);
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    ExtensionContext.Store contextStore = context.getStore(Namespace.create(context.getRequiredTestClass()));
    Optional<Class<?>> testClass = context.getTestClass();
    testClass.ifPresentOrElse(c -> {
      WebBrowser value = c.getAnnotation(UseWebDriver.class).value();
      WebDriverManager webDriverManager;
      switch (value) {
        case WebBrowser.CHROME -> webDriverManager = WebDriverManager.chromedriver();
        case WebBrowser.FIREFOX -> webDriverManager = WebDriverManager.firefoxdriver();
        case null, default -> throw new AssertionError("Wrong WebDriver Definition");
      }
      webDriverManager.setup();
      contextStore.put(STORE_KEY_WEB_DRIVER_MANAGER, webDriverManager);
    }, () -> {
      throw new AssertionError("No WebDriver found");
    });
  }

  public enum WebBrowser {
    CHROME, FIREFOX;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE})
  @ExtendWith(WebdriverResolver.class)
  public @interface UseWebDriver {
    WebBrowser value() default CHROME;
  }
}
