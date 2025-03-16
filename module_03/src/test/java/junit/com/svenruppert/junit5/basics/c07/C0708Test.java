package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;

public class C0708Test {
  //Tagging filtering
  @Nested @Tag("GrpA")
class TestGrpA{
  @Test @Tag("fast") void test001A() { out.println("test001A"); }
  @Test @Tag("slow") void test002A() { out.println("test002A"); }
}

@Nested @Tag("GrpB")
class TestGrpB{
  @Test @Tag("slow") void test001B() { out.println("test001B"); }
  @Test @Tag("fast") void test002B() { out.println("test002B"); }
}

}
