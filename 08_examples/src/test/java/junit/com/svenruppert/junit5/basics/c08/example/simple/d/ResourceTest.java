package junit.com.svenruppert.junit5.basics.c08.example.simple.d;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ClassScopedResourceExtension.class)
public class ResourceTest {

  @Test
  void testUsingResource() {
    System.out.println("Führe Test mit Ressource aus");
  }

  @Test
  void anotherTestUsingResource() {
    System.out.println("Führe weiteren Test mit Ressource aus");
  }
}
