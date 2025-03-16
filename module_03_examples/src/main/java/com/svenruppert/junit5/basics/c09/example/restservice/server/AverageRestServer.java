package com.svenruppert.junit5.basics.c09.example.restservice.server;

import com.sun.net.httpserver.HttpServer;
import com.svenruppert.dependencies.core.logger.HasLogger;

import java.io.IOException;
import java.net.InetSocketAddress;

public class AverageRestServer implements HasLogger {

  public static final String PATH = "/average";
  public int port = 8080;
  private HttpServer server;


  public void startServer() throws IOException {
    startServer(port);
  }

  public void startServer(int port) throws IOException {
    logger().info("startServer on port {}", port);
    this.port = port;
    server = createServer(port);
    server.start();
  }

  public void stopServer() {
    logger().info("stopServer");
    if (server != null) {
      server.stop(0);
    }
  }

  private HttpServer createServer(int port) throws IOException {
    var server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext(PATH, new AverageHandler());
    server.setExecutor(null); // default executor
    return server;
  }

  public int getPort() {
    return port;
  }
}
