package junit.com.svenruppert.junit5.basics.c09.example.simple.e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(HierarchicalContextExtension.class)
public class HierarchyTest {

  @Test
  void testWithClassInfo() {
    System.out.println("Test mit Zugriff auf klassenweite Info");
  }

  @Test
  void anotherTestWithClassInfo() {
    System.out.println("Ein weiterer Test mit Zugriff auf klassenweite Info");
  }
}

