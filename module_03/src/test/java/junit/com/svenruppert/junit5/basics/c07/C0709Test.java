package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.*;


//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class C0709Test {


  @Test @Order(3) @DisplayName("Y") void test00C() {}
  @Test @Order(2) @DisplayName("Z") void test00A() {}
  @Test @Order(1) @DisplayName("X") void test00B() {}
}
