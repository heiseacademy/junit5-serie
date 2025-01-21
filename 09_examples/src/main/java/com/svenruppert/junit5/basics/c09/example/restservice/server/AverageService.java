package com.svenruppert.junit5.basics.c09.example.restservice.server;

import com.svenruppert.dependencies.core.logger.HasLogger;

import java.util.Map;

public class AverageService implements HasLogger {

  public double calculateAverage(Map<String, Double> keyValuePairs) {
    logger().info("calculateAverage - keyValuePairs: {}", keyValuePairs);
    double result = keyValuePairs.values().stream()
        .mapToDouble(Double::doubleValue)
        .average()
        .orElse(0.0);
    logger().info("calculateAverage - result: {}", result);
    return result;
  }
}
