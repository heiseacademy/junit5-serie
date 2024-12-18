package junit.com.svenruppert.junit5.basics.c08.example.refactored.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MockHttpExchange extends HttpExchange {
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
