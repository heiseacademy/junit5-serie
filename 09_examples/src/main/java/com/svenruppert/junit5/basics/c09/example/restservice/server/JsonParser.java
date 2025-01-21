package com.svenruppert.junit5.basics.c09.example.restservice.server;

import com.svenruppert.dependencies.core.logger.HasLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class JsonParser implements HasLogger {

  public static final String JSON_SEPARATOR = ",";
  public static final String VALUE_SEPARATOR = ":";

  public Map<String, Double> parseJson(String json) {
    logger().info("Parsing JSON: {}", json);
    Map<String, Double> result = json
        .trim()
        .replace(" ", "")
        .replace("{", "")
        .replace("}", "")
        .replace("\"", "")
        .lines()
        .peek(line -> logger().info("line {}", line))
        .flatMap(line -> {
          logger().info("Parsing line: {}", line);
          String[] keyValuePairs = line.split(JSON_SEPARATOR);
          logger().info("keyValuePairs: {}", keyValuePairs);
          List<Map.Entry<String, Double>> entries = new ArrayList<>(keyValuePairs.length);
          for (String keyValuePair : keyValuePairs) {
            String[] values = keyValuePair.split(VALUE_SEPARATOR);
            String date = values[0];
            String doubleValue = values[1];
            logger().info("date: {}", date);
            logger().info("doubleValue: {}", doubleValue);
            Map.Entry<String, Double> entry = Map.entry(date, Double.parseDouble(doubleValue));
            entries.add(entry);
          }
          return entries.stream();
        })
        .peek(entry -> {
          logger().info("peek entry: {}", entry);
        })
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    logger().info("parseJson - result: {}", result);
    return result;
  }


  public String convertDataToJson(Map<String, Double> keyValuePairs) {
    StringBuilder jsonBuilder = new StringBuilder();
    jsonBuilder.append("{");
    for (Map.Entry<String, Double> entry : keyValuePairs.entrySet()) {
      jsonBuilder.append("\"")
          .append(entry.getKey())
          .append("\":")
          .append(entry.getValue());
      jsonBuilder.append(",");
    }
    // Remove the last comma and close the JSON
    if (!keyValuePairs.isEmpty()) {
      jsonBuilder.setLength(jsonBuilder.length() - 1);
    }
    jsonBuilder.append("}");

    String json = jsonBuilder.toString();
    return json;
  }
}
