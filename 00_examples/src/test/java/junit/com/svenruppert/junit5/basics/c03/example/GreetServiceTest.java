package junit.com.svenruppert.junit5.basics.c03.example;

import com.svenruppert.junit5.basics.c03.example.GreetService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetServiceTest {
  @Test
  public void testUpperCaseWithBlanks() {
    GreetService greetService = new GreetService();
    // Test case 3: String with spaces
    String result3 = greetService.processData("Hello World");
    assertEquals("Hello HELLO_WORLD!", result3);
  }

  @Test
  void testLowerCaseShort() {
    GreetService greetService = new GreetService();
    // Test case 2: String length less than or equal to 5
    String result2 = greetService.processData("Hi");
    assertEquals("Hello hi!", result2);
  }

  @Test
  void testUpperCaseLong() {
    GreetService greetService = new GreetService();
    // Test case 1: String length greater than 5
    String result1 = greetService.processData("HelloWorld");
    assertEquals("Hello HELLOWORLD!", result1);
  }
}