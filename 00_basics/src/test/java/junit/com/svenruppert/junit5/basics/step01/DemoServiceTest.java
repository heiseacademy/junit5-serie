package junit.com.svenruppert.junit5.basics.step01;

import com.svenruppert.junit5.basics.step01.DemoService;
import org.junit.jupiter.api.Test;

class DemoServiceTest {


  @Test
  void doSomeWorkA() {
    //here should be something
    DemoService demoService = new DemoService();
    String result = demoService.doSomeWork(10);
  }

  @Test
  void test001() {
    throw new NullPointerException("Einfach so");
  }
}