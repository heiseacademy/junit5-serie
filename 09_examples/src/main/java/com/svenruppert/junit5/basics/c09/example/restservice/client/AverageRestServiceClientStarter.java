package com.svenruppert.junit5.basics.c09.example.restservice.client;

import com.svenruppert.dependencies.core.logger.HasLogger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class AverageRestServiceClientStarter
    implements HasLogger {

  private AverageRestServiceClientStarter() {
  }

  public static void main(String[] args) throws IOException {

    AverageRestServiceClientStarter starter = new AverageRestServiceClientStarter();
    HttpURLConnection con = starter.newConnection("localhost", "8080", "/average");
    AverageRestServiceClient client = new AverageRestServiceClient(con);

    client.connect();
    Double average = client.sendKeyValuePairs(starter.testData());
    System.out.println(average);
    client.disconnect();

  }

  private HttpURLConnection newConnection(String target, String port, String path) {
    String url = "http://" + target + ":" + port + path;
    logger().info("Using target: {}", url);
    try {
      URI uri = URI.create(url);
      return (HttpURLConnection) uri.toURL().openConnection();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Map<String, Double> testData() {
    Map<String, Double> data = new HashMap<>();
    data.put("2025.01.01", 1.0);
    data.put("2025.01.02", 4.0);
    data.put("2025.01.04", 1.0);
    return data;
  }
}
