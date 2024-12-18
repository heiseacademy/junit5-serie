package junit.com.svenruppert.junit5.basics.c08.example.refactored.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.svenruppert.junit5.basics.c08.example.restservice.GreetHandler;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GreetHandlerTest {

  @Test
  void testHandlePostRequest() throws IOException {
    // Setup HttpExchange for POST request
    String inputData = "Max, Mustermann";
    HttpExchange exchange = new MockHttpExchange("/greet", "POST", inputData);

    // Handle the request
    new GreetHandler().handle(exchange);

    // Assertions
    assertEquals(200, exchange.getResponseCode());

    Headers responseHeaders = exchange.getResponseHeaders();
    List<String> actual = responseHeaders.get("Content-Type");
    assertTrue(actual.contains("application/json"),
        "The list should contain the value 'application/json'");

    String response = exchange.getResponseBody().toString();
    assertEquals("Hello MAX,_MUSTERMANN!", response);
  }

  @Test
  void testHandleGetRequest() throws IOException {
    // Setup HttpExchange for GET request
    MockHttpExchange exchange = new MockHttpExchange("/greet", "GET", null);

    // Handle the request
    new GreetHandler().handle(exchange);

    // Assertions
    assertEquals(405, exchange.getResponseCode());
    OutputStream outputStream = exchange.getResponseBody();
    assertNotNull(outputStream);
    String response = outputStream.toString();
    assertTrue(response.isEmpty());
  }


}
