package junit.com.svenruppert.junit5.basics.c05;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

public class C0505Test {

  //@TestInstance

  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  public static class TestClass{

    public TestClass(){
      System.out.println("TestClass Constructor");
    }

    @BeforeAll
    public  void beforeAll(){
      System.out.println("TestClass Before All");
    }

    @Test
    void test001() {
    }

    @Test
    void test002() {

    }
  }


}
