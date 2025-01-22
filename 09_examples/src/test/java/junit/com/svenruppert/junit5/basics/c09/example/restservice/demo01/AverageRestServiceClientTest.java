package junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01;

import com.svenruppert.junit5.basics.c09.example.restservice.client.AverageRestServiceClient;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.Http;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.client.AverageRestServiceClientParameterResolver;
import junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.server.AverageRestService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.svenruppert.junit5.basics.c09.example.restservice.server.AverageRestServer.PATH;
import static junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01.AverageServiceTestDataSets.*;
import static junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.client.AverageRestServiceClientParameterResolver.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AverageRestService(port = AverageRestServiceClientTest.COMMUNICATION_PORT)
@AverageRestServiceClientLifeCycle()
public class AverageRestServiceClientTest {

  public static final int COMMUNICATION_PORT = 8890;

  private void calculateAndTestResult(AverageRestServiceClient client,
                                      TestDataSet testDataSet)
      throws IOException {

    client.connect();
    Double result = client.sendKeyValuePairs(testDataSet.data());
    client.disconnect();
    assertEquals(
        testDataSet.average(),
        result,
        testDataSet.delta());
  }

  @Test
  public void testSendKeyValuePairs_withValidData(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {
    TestDataSet testDataSet = withValidData();
    calculateAndTestResult(client, testDataSet);
  }

  @Test
  public void testSendKeyValuePairs_withEmptyData(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {
    TestDataSet testDataSet = withEmptyData();
    calculateAndTestResult(client, testDataSet);
  }

  @Test
  public void testSendKeyValuePairs_withSingleEntry(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {
    TestDataSet testDataSet = withSingleEntry();
    calculateAndTestResult(client, testDataSet);
  }

  @Test
  public void testSendKeyValuePairs_withMixedValues(
      @Http(port = COMMUNICATION_PORT, path = PATH) AverageRestServiceClient client)
      throws IOException {
    TestDataSet testDataSet = withMixedValues();
    calculateAndTestResult(client, testDataSet);
  }


}
