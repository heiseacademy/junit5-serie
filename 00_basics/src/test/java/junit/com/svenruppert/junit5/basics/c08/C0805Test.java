package junit.com.svenruppert.junit5.basics.c08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static junit.com.svenruppert.junit5.basics.c08.C0805Test.DBLifeCycle.KEY;
import static junit.com.svenruppert.junit5.basics.c08.C0805Test.DBLifeCycle.NAMESPACE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class C0805Test {

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.PARAMETER)
  @ExtendWith(DBConnectionParameterResolver.class)
  public @interface DB {
  }

  @ExtendWith(DBLifeCycle.class)
  public static class DemoClass {
    @Test
    void test001(@DB DBConnection connection) {
      assertNotNull(connection);
      connection.execute("CREATE DATABASE IF NOT EXISTS test");
    }
  }

  public static class DBConnection {
    private String url;

    public DBConnection(String url) {
      this.url = url;
    }

    public void connect() {
      System.out.println("..connected to " + url);
    }

    public String execute(String query) {
      System.out.println("..execute query " + query);
      return "executed .. " + query;
    }

    public void disconnect() {
      System.out.println("...disconnected from " + url);
    }
  }

  public static class DBConnectionParameterResolver
      implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
        throws ParameterResolutionException {
      System.out.println("supportsParameter()");
      return parameterContext.getParameter().getType() == DBConnection.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
      System.out.println("resolveParameter()");
      ExtensionContext.Store contextStore = extensionContext.getStore(NAMESPACE);
      return contextStore.get(KEY, DBConnection.class);
    }
  }

  public static class DBLifeCycle
      implements BeforeAllCallback, AfterAllCallback {
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(DBLifeCycle.class);
    public static String KEY = DBConnection.class.getSimpleName();

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
      System.out.println("afterAll()");
      ExtensionContext.Store ctxStore = context.getStore(NAMESPACE);
      DBConnection dbConnection = ctxStore.get(KEY, DBConnection.class);
      dbConnection.disconnect();
      ctxStore.remove(KEY);
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
      System.out.println("beforeAll()");
      ExtensionContext.Store ctxStore = context.getStore(NAMESPACE);
      DBConnection connection = new DBConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
      connection.connect();
      ctxStore.put(KEY, connection);
    }
  }

}