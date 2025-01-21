package junit.com.svenruppert.junit5.basics.c09.example.restservice.extensions.server;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(AverageRestServiceLifeCycle.class)
public @interface AverageRestService {
  int port() default -1;
}
