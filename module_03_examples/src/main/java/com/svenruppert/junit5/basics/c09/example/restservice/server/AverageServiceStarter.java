package com.svenruppert.junit5.basics.c09.example.restservice.server;

import java.io.IOException;

public class AverageServiceStarter {

  private AverageServiceStarter() {
  }

  public static void main(String[] args) throws IOException {
    AverageRestServer averageRestServer = new AverageRestServer();
    averageRestServer.startServer();
  }
}
