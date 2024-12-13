package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class C0712Test {

  //Timeouts


  @Test
  @Timeout(value = 5, unit = TimeUnit.MILLISECONDS, threadMode = Timeout.ThreadMode.SAME_THREAD)
  void test001() {
    try {
      TimeUnit.MILLISECONDS.sleep(2);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }
}
