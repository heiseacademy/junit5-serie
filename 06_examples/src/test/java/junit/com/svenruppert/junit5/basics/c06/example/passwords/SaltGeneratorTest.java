package junit.com.svenruppert.junit5.basics.c06.example.passwords;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.SaltGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SaltGeneratorTest
    implements HasLogger {
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
  @ValueSource(ints = {10,20,30,40,50,60,70,80,90})
  @ValueSource(ints = {100,200,300,400,500,600,700,800,900})
  void generateSalt(int salt) {
    SaltGenerator saltGenerator = new SaltGenerator();
    Set<String> saltSet = new HashSet<>();
    for (int i = 0; i < 100_000; i++) {
      String generated = saltGenerator.generateSalt(salt);
      boolean contains = saltSet.contains(generated);
      if (contains) {
        logger().info("salt = {}", salt);
        logger().info("i = {}", i);
        logger().info("generated = {}", generated);
      }
      assertFalse(contains);
      saltSet.add(generated);
    }
  }

}