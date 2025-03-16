package junit.com.svenruppert.junit5.basics.c05;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class C0503Test {

  //@BeforeAll and @AfterAll

  public static class C0503TestSub {
    @BeforeAll
    public static void beforeAll() {
      System.out.println("Before All C0503TestSub");
    }

    @AfterAll
    public static void afterAll() {
      System.out.println("After All C0503TestSub");
    }
  }

  public static class C0503TestExtended extends C0503TestSub {
    @BeforeAll
    public static void beforeAll() {
      System.out.println("Before All C0503TestExtended");
    }

    @AfterAll
    public static void afterAll() {
      System.out.println("After All C0503TestExtended");
    }

    @Test
    void test001() {
      System.out.println("test001");
    }

    @Test
    void test002() {
      System.out.println("test002");
    }
  }

}
