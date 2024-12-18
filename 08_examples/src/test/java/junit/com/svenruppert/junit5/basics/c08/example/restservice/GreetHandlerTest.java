package junit.com.svenruppert.junit5.basics.c08.example.restservice;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import com.svenruppert.junit5.basics.c08.example.restservice.GreetHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GreetHandlerTest {

  private GreetHandler greetHandler;

  @BeforeEach
  public void setUp() {
    greetHandler = new GreetHandler();
  }

  @Test
  public void testHandlePostRequest() throws IOException {
    // Setup HttpExchange for POST request
    String inputData = "Max, Mustermann";
    HttpExchange exchange = new MockHttpExchange("/greet", "POST", inputData);

    // Handle the request
    greetHandler.handle(exchange);

    // Assertions
    assertEquals(200, exchange.getResponseCode());

    Headers responseHeaders = exchange.getResponseHeaders();
    List<String> actual = responseHeaders.get("Content-Type");
    assertTrue(actual.contains("application/json"), "The list should contain the value 'application/json'");

    String response = exchange.getResponseBody().toString();
    assertEquals("Hello MAX,_MUSTERMANN!", response);
  }

  @Test
  public void testHandleGetRequest() throws IOException {
    // Setup HttpExchange for GET request
    MockHttpExchange exchange = new MockHttpExchange("/greet", "GET", null);

    // Handle the request
    greetHandler.handle(exchange);

    // Assertions
    assertEquals(405, exchange.getResponseCode());
    OutputStream outputStream = exchange.getResponseBody();
    assertNotNull(outputStream);
    String response = outputStream.toString();
    assertTrue(response.isEmpty());
  }


  private static class MockHttpExchange extends HttpExchange {
    private final String requestMethod;
    private final ByteArrayOutputStream responseBody = new ByteArrayOutputStream();
    private final Map<String, String> requestHeaders = new HashMap<>();
    private final Map<String, String> responseHeaders = new HashMap<>();
    private final InputStream requestBody;
    private int responseCode;

    public MockHttpExchange(String path, String requestMethod, String requestBody) {
      this.requestMethod = requestMethod;
      this.requestBody = requestBody == null ? new ByteArrayInputStream(new byte[0]) :
          new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Headers getRequestHeaders() {
      Headers headers = new Headers();
      requestHeaders.forEach(headers::add);
      return headers;
    }

    @Override
    public Headers getResponseHeaders() {
      Headers headers = new Headers();
      List.of("application/json", "text/plain")
          .forEach(v -> headers.add("Content-Type", v));
      responseHeaders.forEach(headers::add);
      return headers;
    }

    @Override
    public InputStream getRequestBody() {
      return requestBody;
    }

    @Override
    public OutputStream getResponseBody() {
      return responseBody;
    }

    @Override
    public void sendResponseHeaders(int rCode, long responseLength) throws IOException {
      this.responseCode = rCode;
    }

    public int getResponseCode() {
      return responseCode;
    }


    @Override
    public URI getRequestURI() {
      return null;
    }

    @Override
    public String getRequestMethod() {
      return requestMethod;
    }

    @Override
    public HttpContext getHttpContext() {
      return null;
    }

    @Override
    public HttpPrincipal getPrincipal() {
      return null;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
      return null;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
      return null;
    }

    @Override
    public String getProtocol() {
      return "";
    }

    @Override
    public Object getAttribute(String name) {
      return null;
    }

    @Override
    public void setAttribute(String name, Object value) {

    }

    @Override
    public void setStreams(InputStream i, OutputStream o) {

    }

    @Override
    public void close() {
    }
  }

}
