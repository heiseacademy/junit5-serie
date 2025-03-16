package junit.com.svenruppert.junit5.basics.c08;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class C0803ATest implements HasLogger {

  private static final Logger log = LoggerFactory.getLogger(C0803ATest.class);

  public static class BeforeAfterEachExtension
      implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
      log.info("After Each Extension activated");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
      log.info("Before Each Extension activated");
    }
  }

  public static class BeforeAfterAllExtension
      implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
      log.info("After All Extension activated");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
      log.info("Before All Extension activated");
    }
  }


  @Test @ExtendWith(BeforeAfterEachExtension.class)
  void test002() {}

  @Nested
  @ExtendWith(BeforeAfterAllExtension.class)
  class TestClass {
    @Test
    void test001() { }
  }

}
