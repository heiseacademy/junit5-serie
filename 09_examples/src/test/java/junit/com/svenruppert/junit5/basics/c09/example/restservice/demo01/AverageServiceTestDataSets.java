package junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01;

import java.util.HashMap;
import java.util.Map;

import static junit.com.svenruppert.junit5.basics.c09.example.restservice.demo01.AverageServiceTestDataSets.TestDatSetBuilder.newBuilder;

public class AverageServiceTestDataSets {

  public static TestDataSet withValidData() {
    return newBuilder()
        .setTestName("withValidData")
        .setAverage(-5.43)
        .setDelta(0.01)
        .addData("2025-01-20", -5.0)
        .addData("2025-01-21", -3.5)
        .addData("2025-01-22", -7.8)
        .build();
  }

  public static TestDataSet withEmptyData() {
    return new TestDataSet("withEmptyData",Map.of(), 0.0, 0.0);
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

  public static class TestDatSetBuilder {
    private final Map<String, Double> data = new HashMap<>();
    private Double average;
    private Double delta;
    private String testName;

    private TestDatSetBuilder() {
    }

    public static TestDatSetBuilder newBuilder(){
      return new TestDatSetBuilder();
    }

    public TestDatSetBuilder addData(String key, Double value) {
      if (value == null) {
        throw new IllegalArgumentException("value for map is null");
      }
      if (key == null) {
        throw new IllegalArgumentException("data for key is null");
      }
      data.put(key, value);
      return this;
    }

    public TestDatSetBuilder setAverage(Double average) {
      this.average = average;
      return this;
    }

    public TestDatSetBuilder setDelta(Double delta) {
      this.delta = delta;
      return this;
    }

    public TestDatSetBuilder setTestName(String testName) {
      this.testName = testName;
      return this;
    }

    public TestDataSet build() {
      return new TestDataSet(
          this.testName, this.data, this.average, this.delta);
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
