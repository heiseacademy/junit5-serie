package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class C0705Test {

  public static class Calculator {
    private int result;

    public Calculator() { this.result = 0; }

    public void reset(){ result = 0; }
    public void resetTo(int a){ result = a; }

    public int add(int a) { return result + a;}
    public int sub(int a) { return result - a; }
    public int mul(int a) { return result * a; }
    public int div(int a) {
      if (a == 0) {
        throw new ArithmeticException("Division by zero is not allowed.");
      }
      return result / a;
    }
  }
  public interface DemoTest {

    @BeforeEach
    default void doBefore(){
      System.out.println("Before each test");
      getCalculator().reset();
    }

    @AfterEach
    default void doAfter(){
      System.out.println("After each test");
      getCalculator().reset();
    }

    @Test
    default void divideByZero(){
      Calculator calculator = getCalculator();
      assertNotNull(calculator);
      assertThrows(ArithmeticException.class,()-> calculator.div(0));
    }
    Calculator getCalculator();
  }

  public static class AddTests
      implements DemoTest {

    @Test
    void additionTest() {
      assertEquals(2, getCalculator().add(2));
    }

    @Override
    public Calculator getCalculator() {
      return new Calculator();
    }
  }

}
