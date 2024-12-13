package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class C0703Test {

  @Test
  void test001() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 001 " + currentThread().getName());
  }

  @Test
  void test002() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 002 " + currentThread().getName());
  }

  @Test
  void test003() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 003 " + currentThread().getName());
  }

  @Test
  void test004() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 004 " + currentThread().getName());
  }

  @Test
  void test005() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 005 " + currentThread().getName());
  }

  @Test
  void test006() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 006 " + currentThread().getName());
  }

  @Test
  void test007() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 007 " + currentThread().getName());
  }

  @Test
  void test008() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 008 " + currentThread().getName());
  }

  @Test
  void test009() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 009 " + currentThread().getName());
  }

  @Test
  void test010() throws InterruptedException {
    SECONDS.sleep(2);
    out.println("Running Test 010 " + currentThread().getName());
  }
}
