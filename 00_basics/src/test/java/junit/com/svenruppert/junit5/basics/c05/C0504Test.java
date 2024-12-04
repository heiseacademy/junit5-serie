package junit.com.svenruppert.junit5.basics.c05;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

public class C0504Test {

  //@Suite , @BeforeSuite , @AfterSuite
  @Suite
  @SelectClasses({C0504TestClass01.class, C0504TestClass02.class})
  @SelectPackages({"junit.com.svenruppert.junit5.basics.c05", ""})
  @IncludeClassNamePatterns({""})
  public static class C0504TestSuite {

  }

  public static class C0504TestClass01 {

    @BeforeEach
    public void beforeEach() {
      System.out.println("Before each");
    }

    @AfterAll
    public static void afterAll() {
      System.out.println("After all");
    }
    @Test void test001() {}
    @Test void test002() {}
  }
  public static class C0504TestClass02 {
    @Test void test003() {}
    @Test void test004() {}
  }
  public static class C0504TestClass03 {
    @Test void test005() {}
    @Test void test006() {}
  }

}
