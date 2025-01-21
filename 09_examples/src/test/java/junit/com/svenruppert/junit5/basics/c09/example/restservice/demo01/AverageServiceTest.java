package junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01;

import com.svenruppert.junit5.basics.c09.example.restservice.server.AverageService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageServiceTest {

  @Test
  public void testCalculateAverage_withValidData() {
    Map<String, Double> data = Map.of(
        "2025-01-20", -5.0,
        "2025-01-21", -3.5,
        "2025-01-22", -7.8
    );
    double result = new AverageService().calculateAverage(data);
    assertEquals(-5.43, result, 0.01, "The average should be -5.43");
  }

  @Test
  public void testCalculateAverage_withEmptyData() {
    Map<String, Double> data = Map.of();
    double result = new AverageService().calculateAverage(data);
    assertEquals(0.0, result, "The average of an empty map should be 0.0");
  }

  @Test
  public void testCalculateAverage_withSingleEntry() {
    Map<String, Double> data = Map.of(
        "2025-01-20", 15.0
    );
    double result = new AverageService().calculateAverage(data);
    assertEquals(15.0, result, "The average of a single entry should be the value itself");
  }

  @Test
  public void testCalculateAverage_withMixedValues() {
    Map<String, Double> data = Map.of(
        "2025-01-20", -10.0,
        "2025-01-21", 20.0,
        "2025-01-22", 0.0
    );
    double result = new AverageService().calculateAverage(data);
    assertEquals(3.33, result, 0.01, "The average should be 3.33");
  }
}