package junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01;

import java.util.HashMap;
import java.util.Map;

public class AverageServiceTestDataSets {

  public static TestDataSet withValidData() {
    return TestDataSetBuilder
        .newBuilder()
        .setTestName("withValidData")
        .setAverage(-5.43)
        .setDelta(0.01)
        .addData("2025-01-20", -5.0)
        .addData("2025-01-21", -3.5)
        .addData("2025-01-22", -7.8)
        .build();
  }

  public static TestDataSet withEmptyData() {
    return new TestDataSet("withEmptyData", Map.of(), 0.0, 0.0);
  }

  public static TestDataSet withSingleEntry() {
    return new TestDataSet(
        "withSingleEntry",
        Map.of("2025-01-20", 15.0),
        15.0,
        0.0);
  }

  public static TestDataSet withMixedValues() {
    return new TestDataSet(
        "withMixedValues",
        Map.of(
            "2025-01-20", -10.0,
            "2025-01-21", 20.0,
            "2025-01-22", 0.0
        ),
        3.33);
  }


  public static class TestDataSetBuilder {
    private final Map<String, Double> data = new HashMap<>();
    private Double average;
    private Double delta;
    private String testName;

    private TestDataSetBuilder() {
    }

    public static TestDataSetBuilder newBuilder() {
      return new TestDataSetBuilder();
    }

    public TestDataSetBuilder addData(String key, Double value) {
      if (key == null) {
        throw new IllegalArgumentException("key cannot be null");
      }
      if (value == null) {
        throw new IllegalArgumentException("value cannot be null");
      }
      data.put(key, value);
      return this;
    }


    public TestDataSetBuilder setAverage(Double average) {
      this.average = average;
      return this;
    }

    public TestDataSetBuilder setDelta(Double delta) {
      this.delta = delta;
      return this;
    }

    public TestDataSetBuilder setTestName(String testName) {
      this.testName = testName;
      return this;
    }

    public TestDataSet build() {
      return new TestDataSet(this.testName, this.data, this.average, this.delta);
    }
  }


  public record TestDataSet(
      String testName,
      Map<String, Double> data,
      Double average,
      Double delta) {

    public TestDataSet(String testName,
                       Map<String, Double> data,
                       Double average) {
      this(testName, data, average, 0.01);
    }

    public TestDataSet(String testName,
                       Map<String, Double> data,
                       Double average,
                       Double delta) {
      this.testName = testName;
      this.data = data;
      this.average = average;
      this.delta = delta;
    }
  }


}
