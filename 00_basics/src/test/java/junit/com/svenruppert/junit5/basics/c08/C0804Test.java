package junit.com.svenruppert.junit5.basics.c08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class C0804Test {

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Extensions({
      @ExtendWith(BeforeEachA.class),
      @ExtendWith(BeforeEachB.class),
      @ExtendWith(AfterEachC.class),
      @ExtendWith(AfterEachD.class)
  })
  public @interface MyLifeCycle {
  }

  public static class BeforeEachA implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
      System.out.println("BeforeEachA");
    }
  }

  public static class BeforeEachB implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
      System.out.println("BeforeEachB");
    }
  }

  public static class AfterEachC implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
      System.out.println("AfterEachC");
    }
  }

  public static class AfterEachD implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
      System.out.println("AfterEachD");
    }
  }

  @MyLifeCycle
  public static class DemoClass {
    @Test
    void test001() {
      System.out.println("test001");
    }
  }
}