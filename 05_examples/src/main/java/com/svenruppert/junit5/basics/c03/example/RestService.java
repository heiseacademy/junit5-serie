package com.svenruppert.junit5.basics.c03.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RestService {

  public static final String PATH = "/greet";
  public static final int PORT = 8080;
  private static HttpServer server;

  private RestService() {
  }

  public static void main(String[] args) throws IOException {
    startServer();
  }

  public static void startServer() throws IOException {
    server = createServer();
    server.start();
  }

  public static void stopServer() {
    if (server != null) {
      server.stop(0);
    }
  }

  public static HttpServer createServer() throws IOException {
    var server = HttpServer.create(new InetSocketAddress(PORT), 0);
    server.createContext(PATH, new GreetHandler());
    server.setExecutor(null); // default executor
    return server;
  }

}
