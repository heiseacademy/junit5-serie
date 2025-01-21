package junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01;

import com.svenruppert.junit5.basics.c09.example.restservice.client.AverageRestServiceClient;
import com.svenruppert.junit5.basics.c09.example.restservice.server.AverageRestServer;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.Http;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.client.AverageRestServiceClientParameterResolver;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.server.AverageRestService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static com.svenruppert.junit5.basics.c09.example.restservice.server.AverageRestServer.PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AverageRestService(port = AverageRestServiceClientTest.COMMUNICATION_PORT)
@AverageRestServiceClientParameterResolver.AverageRestServiceClientLifeCycle
public class AverageRestServiceClientTest {

  public static final int COMMUNICATION_PORT = 8890;

  @Test
  public void testSendKeyValuePairs_withValidData(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {

    Map<String, Double> data = Map.of(
        "2025-01-20", -5.0,
        "2025-01-21", -3.5,
        "2025-01-22", -7.8
    );

    client.connect();
    Double result = client.sendKeyValuePairs(data);
    assertEquals(-5.43, result, 0.01, "The average should be -5.43");
    client.disconnect();
  }

  @Test
  public void testSendKeyValuePairs_withEmptyData(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {
    Map<String, Double> data = Map.of();

    client.connect();
    Double result = client.sendKeyValuePairs(data);
    assertEquals(0.0, result, "The average of an empty map should be 0.0");
    client.disconnect();
  }

  @Test
  public void testSendKeyValuePairs_withSingleEntry(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {
    Map<String, Double> data = Map.of(
        "2025-01-20", 15.0
    );

    client.connect();
    Double result = client.sendKeyValuePairs(data);
    assertEquals(15.0, result, "The average of a single entry should be the value itself");
    client.disconnect();
  }

  @Test
  public void testSendKeyValuePairs_withMixedValues(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {
    Map<String, Double> data = Map.of(
        "2025-01-20", -10.0,
        "2025-01-21", 20.0,
        "2025-01-22", 0.0
    );

    client.connect();
    Double result = client.sendKeyValuePairs(data);
    assertEquals(3.33, result, 0.01, "The average should be 3.33");
    client.disconnect();
  }


}
