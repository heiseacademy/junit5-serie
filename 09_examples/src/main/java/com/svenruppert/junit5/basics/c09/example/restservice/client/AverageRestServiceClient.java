package com.svenruppert.junit5.basics.c09.example.restservice.client;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c09.example.restservice.server.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class AverageRestServiceClient implements HasLogger {

  private final HttpURLConnection connection;

  public AverageRestServiceClient(HttpURLConnection connection) {
    this.connection = connection;
  }

  public double parseAverageFromResponse(String response) {
    logger().info("parsing response: {}", response);
    // Extract the average value from the JSON response
    String averageStr = response.replaceAll(".*\"average\":\"(.*?)\".*", "$1");
    logger().info("average string: {}", averageStr);
    String converted = averageStr.replace(",", ".");
    logger().info("converted string: {}", converted);
    return Double.parseDouble(converted);
  }

  public void connect() throws IOException {
    connection.setDoOutput(true);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    logger().info("Connecting to {}", connection.getURL());
    connection.connect();
    logger().info("Connected to {}", connection.getURL());
  }

  public void disconnect() throws IOException {
    if (connection != null) {
      logger().info("Disconnected from {}", connection.getURL());
      connection.disconnect();
    } else {
      logger().info("No connection to disconnect..");
    }
  }

  public Double sendKeyValuePairs(
      Map<String, Double> keyValuePairs)
      throws IOException {

    String json = new JsonParser().convertDataToJson(keyValuePairs);
    logger().info("converted data JSON: {}", json);

    logger().info("Sending data to {}", connection.getURL());
    try (OutputStream os = connection.getOutputStream()) {
      byte[] input = json.getBytes(StandardCharsets.UTF_8);
      os.write(input, 0, input.length);
    }
    logger().info("data was sent to {}", connection.getURL());
    // Read the response
    int responseCode = connection.getResponseCode();
    logger().info("Response code: {}", responseCode);

    logger().info("start reading the results from {}", connection.getURL());
    try (BufferedReader br = new BufferedReader(new InputStreamReader(
        connection.getInputStream(), StandardCharsets.UTF_8))) {
      StringBuilder response = new StringBuilder();
      String responseLine;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine);
      }
      logger().info("The result is {}", response);
      return parseAverageFromResponse(response.toString());
    }
  }


}
