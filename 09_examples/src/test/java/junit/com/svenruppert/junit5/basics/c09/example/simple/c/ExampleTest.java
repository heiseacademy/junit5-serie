package junit.com.svenruppert.junit5.basics.c09.example.simple.c;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestInfoLoggingExtension.class)
public class ExampleTest {

  @Test
  void testOne() {
    System.out.println("Führe TestOne aus");
  }

  @Test
  void testTwo() {
    System.out.println("Führe TestTwo aus");
  }
}

