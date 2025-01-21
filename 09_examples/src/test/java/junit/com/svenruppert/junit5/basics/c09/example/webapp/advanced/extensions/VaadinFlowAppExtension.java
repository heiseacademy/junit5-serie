package junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced.extensions;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c09.example.webapp.AppStarter;
import com.svenruppert.vaadin.nano.CoreUIServiceJava;
import junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced.URLValidator;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class VaadinFlowAppExtension
    implements BeforeEachCallback, AfterEachCallback, HasLogger {


  public static final String STORE_KEY_WEB_APP = "WEB_APP";

  public static ExtensionContext.Namespace webAppNameSpace(ExtensionContext extensionContext) {
    return ExtensionContext.Namespace.create(extensionContext.getRequiredTestMethod());
  }

  @Override
  public void afterEach(ExtensionContext context)
      throws Exception {
    ExtensionContext.Store store = context.getStore(webAppNameSpace(context));
    AppStarter appStarter = store.get(STORE_KEY_WEB_APP, AppStarter.class);
    appStarter.shutdown();
    store.remove(STORE_KEY_WEB_APP);
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    Class<?> testClass = context.getRequiredTestClass();
    VaadinFlowTest annotation = testClass.getAnnotation(VaadinFlowTest.class);
    if (annotation == null) {
      throw new AssertionError("@VaadinFlowTest annotation is missing on class level");
    }

    int port = annotation.port();
    String target = annotation.target();
    boolean isValid = URLValidator.isValidHttpOrHttpsURL(target);
    if (!isValid) {
      throw new AssertionError("VaadinFlowTest annotation: The target URL is not valid");
    }
    System.setProperty("vaadin.compatibilityMode", "false");
    System.setProperty("vaadin.productionMode", "false");
    String cliPort = CoreUIServiceJava.CLI_PORT + "=" + port;
    String cliBasPkg = CoreUIServiceJava.CLI_BASE_PKG + "=" + AppStarter.class.getPackage().getName();
    String[] args = {cliPort, cliBasPkg};

    AppStarter appStarter = new AppStarter();
    appStarter.executeCLI(args).startup();
    ExtensionContext.Store store = context.getStore(webAppNameSpace(context));
    store.put(STORE_KEY_WEB_APP, appStarter);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @ExtendWith(VaadinFlowAppExtension.class)
  @ExtendWith(VaadinFlowWebDriverInitExtension.class)
//  @ExtendWith(FlowSelectorByIDResolver.class)
  @FlowSelectorByIDResolver.FlowSelector
  public @interface VaadinFlowTest {
    String target() default "http://localhost";

    int port() default 8899;

    String elementID();
  }
}
