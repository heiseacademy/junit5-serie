package junit.com.svenruppert.junit5.basics.c09.example.simple.a;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SimpleBeforeAllExtension.class)
public class MyTest {

  @Test
  void testA() {
    System.out.println("Test A läuft");
  }

  @Test
  void testB() {
    System.out.println("Test B läuft");
  }
}

