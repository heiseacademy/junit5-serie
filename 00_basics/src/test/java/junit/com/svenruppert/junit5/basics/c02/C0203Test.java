package junit.com.svenruppert.junit5.basics.c02;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class C0203Test {

  private Calculator calculator;

  @Test
  void testMenthodenName() {
    assumeFalse(System
            .getProperty("os.name")
            .contains("Windows"),
        "Dieser Test wird auf Windows übersprungen");

    //Hier kommt der Test hin.
    int ergebnis = 4 + 3;
    int erwartet = 7;
    assertEquals(erwartet, ergebnis);
  }

  @BeforeEach
  void setUp() {
    calculator = new Calculator();
    System.out.println("Neuer Calculator für den Test initialisiert.");
  }

  @Test
  void testAddition() {
    int result = calculator.add(2, 3);
    assertEquals(5, result, "2 + 3 sollte 5 ergeben");
  }

  @Test
  void testSubtraction() {
    int result = calculator.subtract(5, 2);
    assertEquals(3, result, "5 - 2 sollte 3 ergeben");
  }
}

// Eine einfache Calculator-Klasse für das Beispiel
class Calculator {
  int add(int a, int b) {
    return a + b;
  }

  int subtract(int a, int b) {
    return a - b;
  }
}








