package junit.com.svenruppert.junit5.basics.c08.example.refactored;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;

import static com.svenruppert.junit5.basics.c08.example.restservice.RestService.PATH;
import static com.svenruppert.junit5.basics.c08.example.restservice.RestService.PORT;

public class HttpConnectionExtension
    implements BeforeEachCallback, AfterEachCallback {

  public static final String STORE_KEY = "httpConnection";

  public static @NotNull Namespace createNameSpace(ExtensionContext context) {
    Method requiredTestMethod = context.getRequiredTestMethod();
    return Namespace.create(requiredTestMethod.getName());
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    ExtensionContext.Store store = context.getStore(createNameSpace(context));
    HttpURLConnection httpConnection = store.get(STORE_KEY, HttpURLConnection.class);
    if (httpConnection != null) httpConnection.disconnect();
    store.remove(STORE_KEY);
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    String localhost = "localhost"; //Woher kommt das?
    try {
      URI uri = URI.create("http://" + localhost + ":" + PORT + PATH);
      HttpURLConnection httpConnection
          = (HttpURLConnection) uri
          .toURL()
          .openConnection();
      httpConnection.setRequestMethod("POST");
      httpConnection.setDoOutput(true);
      httpConnection.setRequestProperty("Content-Type", "text/plain");
      httpConnection.connect();

      Namespace namespace = createNameSpace(context);
      ExtensionContext.Store store = context.getStore(namespace);
      store.put(STORE_KEY, httpConnection);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @ExtendWith(HttpConnectionExtension.class)
  public static @interface Connection {
  }
}
