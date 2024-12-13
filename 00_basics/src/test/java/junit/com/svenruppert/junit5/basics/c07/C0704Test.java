package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class C0704Test {

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

  private Calculator calculator;

  @BeforeEach
  void setUp() {
    System.out.println("BeforeEach C0704Test ");
    calculator = new Calculator();
  }

  @Test
  void additionTest() {
    calculator.reset();
    assertEquals(2, calculator.add(2));
  }

  @Nested
  class SubstractionTests {

    @BeforeEach
    void setUp() {
      System.out.println("BeforeEach SubstractionTests ");
      calculator.resetTo(5);
    }

    @Test
    void subPosNumbers() {

      assertEquals(3, calculator.sub(2));
    }
    @Test
    void subNegNumbers() {
      assertEquals(-1, calculator.sub(6));
    }
  }

}
