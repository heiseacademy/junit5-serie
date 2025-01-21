package junit.com.svenruppert.junit5.basics.c08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class C0803BTest {

  private static final Logger log = LoggerFactory.getLogger(C0803BTest.class);

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  public @interface FieldMarker{}


  @ExtendWith(ServiceFactoryFieldPostProcessor.class)
  static class DemoClass {

    @FieldMarker private Service service;
    private String demoField;

    @Test
    void test001() {
      assertNotNull(service);
      String helloWorld = service.doWork("Hello World");
      assertNotNull(helloWorld);
      assertEquals("Hello World processed by Service", helloWorld);
    }
  }

  public static class Service {
    public String doWork(String input){ return input + " processed by Service"; }
  }

  public static class ServiceFactoryFieldPostProcessor
      implements TestInstancePostProcessor{


    @Override
    public void postProcessTestInstance(Object testInstance,
                                        ExtensionContext context)
        throws Exception {

      Class<?> testInstanceClass = testInstance.getClass();
      log.info(testInstanceClass.getName());

      Field[] fields = testInstanceClass.getDeclaredFields();
      for (Field field : fields) {
        log.info(field.getName());
        if(field.isAnnotationPresent(FieldMarker.class)){
          field.setAccessible(true);
          Service service = new Service();
          field.set(testInstance, service);
          field.setAccessible(false);
        }
      }
    }
  }

}