package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class C0703Test {

  //parallel Execution
  //junit-platform.properties
  //junit.jupiter.execution.parallel.enabled = true
  //junit.jupiter.execution.parallel.mode.default = concurrent
  //junit.jupiter.execution.parallel.config.strategy = dynamic

  @Test
  void test1() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 1 - " + Thread.currentThread().getName());
  }

  @Test
  void test2() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 2 - " + Thread.currentThread().getName());
  }

  @Test
  void test3() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 3 - " + Thread.currentThread().getName());
  }

  @Test
  void test4() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 4 - " + Thread.currentThread().getName());
  }

  @Test
  void test5() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 5 - " + Thread.currentThread().getName());
  }

  @Test
  void test6() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 6 - " + Thread.currentThread().getName());
  }

  @Test
  void test7() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 7 - " + Thread.currentThread().getName());
  }

  @Test
  void test8() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Running Test 8 - " + Thread.currentThread().getName());
  }

}
