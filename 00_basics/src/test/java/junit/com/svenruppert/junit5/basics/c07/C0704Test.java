package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  //Nested Tests
  Calculator calculator;

  @BeforeEach
  void setup() {
    calculator = new Calculator();
  }

  @Test
  void additionTest() {
    assertEquals(2, calculator.add(2));
  }

  @Nested
  class SubtractionTests {

    @BeforeEach
    void setup() {
      // Diese Setup-Methode gilt nur für die Tests in dieser Klasse.
      calculator.resetTo(5);
    }

    @Test
    void subtractPositiveNumbers() {
      assertEquals(3, calculator.sub(2));
    }

    @Test
    void subtractWithNegativeResult() {
      assertEquals(-1, calculator.sub(6));
    }
  }

  @Nested
  class DivisionTests {

    @BeforeEach
    void setup() {
      // Diese Setup-Methode gilt nur für die Tests in dieser Klasse.
      calculator.resetTo(6);
    }
    @Test
    void divideByNonZero() {
      assertEquals(2, calculator.div(3));
    }

    @Test
    void divideByZeroThrowsException() {
      // Hier kann eine Exception erwartet werden
      assertThrows(ArithmeticException.class, () -> calculator.div(0));
    }
  }
}
