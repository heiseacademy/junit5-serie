package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class C0702Test {

  @Disabled
  @RepeatedTest(value = 10,
      name = "{displayName} - {currentRepetition}")
  void test001(RepetitionInfo repetitionInfo) {
    int currentRepetition = repetitionInfo.getCurrentRepetition();
    int totalRepetitions = repetitionInfo.getTotalRepetitions();
    int failureCount = repetitionInfo.getFailureCount();
    int failureThreshold = repetitionInfo.getFailureThreshold();

    assertTrue(currentRepetition % 2 == 0);
  }
}
