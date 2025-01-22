package junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions;

import junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.FlowSelectorByID;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.WebdriverResolver.STORE_KEY_WEB_DRIVER;
import static junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.WebdriverResolver.webDriverNameSpace;

public class FlowSelectorByIDResolver
    implements ParameterResolver {

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE, ElementType.PARAMETER})
  @ExtendWith(FlowSelectorByIDResolver.class)
  public @interface FlowSelector {}

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getType().isAssignableFrom(FlowSelectorByID.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
                                 ExtensionContext extensionContext)
      throws ParameterResolutionException {
    ExtensionContext.Store contextStore = extensionContext.getStore(webDriverNameSpace(extensionContext));
    WebDriver webDriver = contextStore.get(STORE_KEY_WEB_DRIVER, WebDriver.class);
    if (webDriver == null) { throw new ParameterResolutionException("No WebDriver found"); }
    return new FlowSelectorByID(webDriver);
  }
}
