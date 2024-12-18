package junit.com.svenruppert.junit5.basics.c08.example.restservice;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.svenruppert.junit5.basics.c08.example.restservice.RestService.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class RestServiceTest {

  private HttpURLConnection httpConnection = null;

  @BeforeAll
  public static void setUp() throws IOException {
    startServer();
  }

  @AfterAll
  public static void tearDownAll() {
    stopServer();
  }

  @BeforeEach
  public void createConnection() {
    //Umgebungsvariable extrahieren
    String localhost = "localhost";
    assumeTrue(localhost.equals("localhost"));
    try {
      URI uri = URI.create("http://" + localhost + ":" + PORT + PATH);
      httpConnection = (HttpURLConnection) uri.toURL().openConnection();
      httpConnection.setRequestMethod("POST");
      httpConnection.setDoOutput(true);
      httpConnection.setRequestProperty("Content-Type", "text/plain");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  void tearDown() {
    if (httpConnection != null) httpConnection.disconnect();
  }


  @Test
  public void testGreetHandlerUpperCase() throws IOException {
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
  public void testGreetHandlerLowerCase() throws IOException {
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
