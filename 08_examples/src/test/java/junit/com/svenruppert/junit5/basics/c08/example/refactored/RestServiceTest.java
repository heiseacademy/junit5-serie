package junit.com.svenruppert.junit5.basics.c08.example.refactored;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MyTest
class RestServiceTest {

  @Test
  void testGreetHandlerUpperCase(HttpURLConnection httpConnection) throws IOException {
    assertEquals("POST", httpConnection.getRequestMethod());

    String requestBody = "Test String";
    try (OutputStream os = httpConnection.getOutputStream()) {
      os.write(requestBody.getBytes(StandardCharsets.UTF_8));
    }

    int responseCode = httpConnection.getResponseCode();
    assertEquals(200, responseCode);

    try (var inputStream = httpConnection.getInputStream();
         var scanner = new Scanner(inputStream)) {
      String response = scanner.useDelimiter("\\A").next();
      assertEquals("Hello TEST_STRING!", response);
    }
  }

  @Test
  void testGreetHandlerLowerCase(HttpURLConnection httpConnection) throws IOException {
    assertEquals("POST", httpConnection.getRequestMethod());

    String requestBody = "aBcDe";
    try (OutputStream os = httpConnection.getOutputStream()) {
      os.write(requestBody.getBytes(StandardCharsets.UTF_8));
    }

    int responseCode = httpConnection.getResponseCode();
    assertEquals(200, responseCode);

    try (var inputStream = httpConnection.getInputStream();
         var scanner = new Scanner(inputStream)) {
      String response = scanner.useDelimiter("\\A").next();
      assertEquals("Hello abcde!", response);
    }
  }

}
