package junit.com.svenruppert.junit5.basics.c05;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class C0502Test {

  private List<String> values;

  @BeforeEach
  public void beforeEach(){
    System.out.println("Before each");
    values = new ArrayList<>();
    values.add("one");
  }
  @AfterEach
  public void afterEach() {
    System.out.println("afterEach");
    values = null;
  }


  @Test
  void test001() {
    Assertions.assertFalse(values.isEmpty());
  }

  public static class BeforeEachClassA {
    @BeforeEach
    public void beforeEach01() {
      System.out.println("beforeEach - 01");
    }
  }

  public static class C0502SubTest
      extends BeforeEachClassA {

    @BeforeEach
    public void beforeEach02() {
      System.out.println("beforeEach - 02");
    }

    @Test
    void test001() {
      System.out.println("test001");
    }
  }


}
