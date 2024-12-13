package junit.com.svenruppert.junit5.basics.c07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class C0707Test {

  //Conditional Test Execution

  public static Boolean letsGo = false;

  @Test
  //@ExtendWith(DemoCondition.class)
  @EnableOnDemo
  //@EnabledOnOs
  void test001() { }


  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @ExtendWith(DemoCondition.class)
  public @interface EnableOnDemo{}


  public static class DemoCondition implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
      //Auswerten der Bedingungen
      boolean run = letsGo;

      return run ? ConditionEvaluationResult.enabled("Bedingung erfüllt")
          : ConditionEvaluationResult.disabled("Bedingung nicht erfüllt");
    }
  }
}
