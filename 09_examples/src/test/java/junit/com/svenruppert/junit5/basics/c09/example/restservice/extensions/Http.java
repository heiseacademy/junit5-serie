package junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Http {
  String target() default "localhost";

  int port() default 8080;

  String path() default "";
}
