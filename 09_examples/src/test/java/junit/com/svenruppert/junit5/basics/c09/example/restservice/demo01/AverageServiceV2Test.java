package junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01;

import com.svenruppert.junit5.basics.c09.example.restservice.server.AverageService;
import org.junit.jupiter.api.Test;

import static junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01.AverageServiceTestDataSets.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageServiceV2Test {

  private static void calculateAndTestResult(TestDataSet testDataSet) {
    AverageService service = new AverageService();
    double result = service.calculateAverage(testDataSet.data());

    assertEquals(
        testDataSet.average(),
        result,
        testDataSet.delta());
  }

  @Test
  public void testCalculateAverage_withValidData() {
    calculateAndTestResult(withValidData());
  }

  @Test
  public void testCalculateAverage_withEmptyData() {
    calculateAndTestResult(withEmptyData());
  }

  @Test
  public void testCalculateAverage_withSingleEntry() {
    calculateAndTestResult(withSingleEntry());
  }

  @Test
  public void testCalculateAverage_withMixedValues() {
    calculateAndTestResult(withMixedValues());
  }
}
