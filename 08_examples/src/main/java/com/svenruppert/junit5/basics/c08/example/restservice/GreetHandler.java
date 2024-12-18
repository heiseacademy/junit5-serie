package com.svenruppert.junit5.basics.c08.example.restservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GreetHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    System.out.println("handle");
    if ("POST".equals(exchange.getRequestMethod())) {
      InputStreamReader isr = new InputStreamReader(
          exchange.getRequestBody(),
          StandardCharsets.UTF_8);
      BufferedReader br = new BufferedReader(isr);
      StringBuilder requestBody = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
        requestBody.append(line);
      }

      GreetService greetService = new GreetService();
      String response = greetService.processData(requestBody.toString());

      exchange.sendResponseHeaders(200, response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    } else {
      exchange.sendResponseHeaders(405, -1); // Method Not Allowed
    }
  }
}
