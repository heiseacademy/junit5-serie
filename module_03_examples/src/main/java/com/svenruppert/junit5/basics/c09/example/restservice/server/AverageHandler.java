package com.svenruppert.junit5.basics.c09.example.restservice.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.svenruppert.dependencies.core.logger.HasLogger;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AverageHandler
    implements HttpHandler, HasLogger {

  private final AverageService averageService = new AverageService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if ("POST".equals(exchange.getRequestMethod())) {
      logger().info("Received POST request");
      // Read the request body
      InputStream is = exchange.getRequestBody();
      String body = new BufferedReader(new InputStreamReader(is, UTF_8))
          .lines()
          .collect(Collectors.joining("\n"));
      logger().info("Body: {}", body);
      // Parse JSON manually
      Map<String, Double> keyValuePairs = new JsonParser().convertJsonToData(body);
      double average = averageService.calculateAverage(keyValuePairs);
      // Format the result
      DecimalFormat df = new DecimalFormat("0.00");
      String response = "{\"average\":\"" + df.format(average) + "\"}";

      // Send the response
      exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
      exchange.sendResponseHeaders(200, response.getBytes(UTF_8).length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes(UTF_8));
      os.close();
    } else {
      exchange.sendResponseHeaders(405, -1); // Method Not Allowed
    }
  }


}
