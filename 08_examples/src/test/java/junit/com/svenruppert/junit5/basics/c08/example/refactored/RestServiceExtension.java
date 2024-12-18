package junit.com.svenruppert.junit5.basics.c08.example.refactored;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.svenruppert.junit5.basics.c08.example.restservice.RestService.startServer;
import static com.svenruppert.junit5.basics.c08.example.restservice.RestService.stopServer;

public class RestServiceExtension
    implements BeforeAllCallback, AfterAllCallback {

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @ExtendWith(RestServiceExtension.class)
  public @interface RestService { }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    stopServer();
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    startServer();
  }
}
