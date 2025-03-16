package junit.com.svenruppert.junit5.basics.c08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.System.out;

class C0804Test {

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Extensions({
      @ExtendWith(BeforeEachA.class),
      @ExtendWith(BeforeEachB.class),
      @ExtendWith(AfterEachD.class),
      @ExtendWith(AfterEachC.class)
  })
  public @interface MyLifeCycle {}

  public static class BeforeEachA implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception { out.println("BeforeEachA");}
  }
  public static class BeforeEachB implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception { out.println("BeforeEachB");}
  }

  public static class AfterEachC implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws Exception { out.println("AfterEachC"); }
  }
  public static class AfterEachD implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws Exception { out.println("AfterEachD"); }
  }

  @MyLifeCycle
  public static class DemoClass {
    @Test
    void test001() { out.println("test001"); }
  }

}