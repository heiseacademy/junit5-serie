package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class C0702Test {

  // bad practices
  private static int counter = 1;

  @RepeatedTest(name = "xx", value = 4, failureThreshold = 1)
  void test001() {
    counter++;
    assertTrue(counter % 2 == 0);
  }


  @RepeatedTest(
      name="{displayName} - {currentRepetition} - {totalRepetitions}",
      value = 4,
      failureThreshold = 2)
  void test002(RepetitionInfo repetitionInfo) {
    int currentRepetition = repetitionInfo.getCurrentRepetition();
    int totalRepetitions = repetitionInfo.getTotalRepetitions();
    int failureCount = repetitionInfo.getFailureCount();
    int failureThreshold = repetitionInfo.getFailureThreshold();

    System.out.println("currentRepetition = " + currentRepetition);
    System.out.println("totalRepetitions = " + totalRepetitions);
    System.out.println("failureThreshold = " + failureThreshold);
    System.out.println("failureCount = " + failureCount);
  }
}
