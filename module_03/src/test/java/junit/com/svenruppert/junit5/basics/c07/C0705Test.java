package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class C0705Test {

  @Test
  void test001(TestInfo testInfo, TestReporter testReporter) {
    assertNotNull(testReporter);
    assertNotNull(testInfo);

    //System.out.println("testInfo.getDisplayName() = " + testInfo.getDisplayName());

    testReporter.publishEntry("PreFix " + testInfo.getDisplayName());
  }

  @RepeatedTest(2)
  void test002(RepetitionInfo repetitionInfo, TestReporter testReporter) {
    assertNotNull(testReporter);
    assertNotNull(repetitionInfo);
    testReporter.publishEntry("Wiederholung Nr. " + repetitionInfo.getCurrentRepetition());
  }
}
