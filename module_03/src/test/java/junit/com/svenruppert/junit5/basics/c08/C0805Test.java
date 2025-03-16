package junit.com.svenruppert.junit5.basics.c08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class C0805Test {

  @ExtendWith(DBLifeCycle.class)
  public static class DemoClass {
    @Test
    void test001(@DB DBConnection connection) {
      assertNotNull(connection);
      String execute = connection.execute("CREATE DATABASE IF NOT EXISTS test");
      assertNotNull(execute);
    }
  }

  public static class DBConnection {
    private String url;
    public DBConnection(String url) {
      this.url = url;
    }

    public String execute(String sql) {
      System.out.println("..execute query " + sql);
      return "executed .. " + sql;
    }
    public void connect() { System.out.println("connect ..." + url); }
    public void disconnect() { System.out.println("disconnect ..." + url); }
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.PARAMETER})
  @ExtendWith(DBConnectionParameterResolver.class)
  public @interface DB {}

  public static class DBConnectionParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
        throws ParameterResolutionException {
      System.out.println("supportsParameter: " + parameterContext.getParameter());
      return parameterContext.getParameter().getType() == DBConnection.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
      System.out.printf("resolveParameter: %s%n", parameterContext.getParameter());

      ExtensionContext.Store store = extensionContext.getStore(DBLifeCycle.NAMESPACE);
      return store.get(DBLifeCycle.KEY, DBConnection.class);
    }
  }


  public static class DBLifeCycle implements BeforeAllCallback, AfterAllCallback {

    public static ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(DBLifeCycle.NAMESPACE_NAME);
    private static final String NAMESPACE_NAME = "DBCONNECTION";
    public static final String KEY = "DBConnectionKEY";

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
      System.out.println("afterAll");
      ExtensionContext.Store store = context.getStore(NAMESPACE);
      DBConnection dbConnection = store.get(KEY, DBConnection.class);
      dbConnection.disconnect();
      store.remove(KEY, DBConnection.class);
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
      System.out.println("beforeAll");
      ExtensionContext.Store store = context.getStore(NAMESPACE);
      DBConnection dbConnection = new DBConnection("jdbc:h2:mem:test");
      dbConnection.connect();
      store.put(DBLifeCycle.KEY, dbConnection);
    }
  }

}