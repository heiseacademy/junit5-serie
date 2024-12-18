package junit.com.svenruppert.junit5.basics.c08.example.simple.b;

import junit.com.svenruppert.junit5.basics.c08.example.simple.a.SimpleBeforeAllExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SimpleBeforeAllExtension.class)
@ExtendWith(ConnectionExtension.class)
public class CombinedTest {

  @Test
  void testSomething() {
    System.out.println("Komplexer Test, der von beiden Extensions profitiert");
  }

  @Test
  void testAnotherThing() {
    System.out.println("Noch ein Test, wieder mit beiden Extensions");
  }
}

