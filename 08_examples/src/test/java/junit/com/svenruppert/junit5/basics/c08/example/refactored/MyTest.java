package junit.com.svenruppert.junit5.basics.c08.example.refactored;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//for IDE support
@Extensions({
    @ExtendWith(RestServiceExtension.class),
    @ExtendWith(HttpConnectionExtension.class),
    @ExtendWith(HttpConnectionParameterResolver.class)
})
//no ide support
//@RestServiceExtension.RestService
//@HttpConnectionExtension.Connection
//@HttpConnectionParameterResolver.HttpConnection
public @interface MyTest {
}
