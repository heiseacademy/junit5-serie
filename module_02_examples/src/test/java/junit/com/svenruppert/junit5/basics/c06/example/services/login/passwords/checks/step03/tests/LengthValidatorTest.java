package junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step03.tests;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.LengthValidator;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.PasswordValidator;
import junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step03.AbstractPasswordValidatorTest;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class LengthValidatorTest extends AbstractPasswordValidatorTest {
  @Override
  protected Stream<InputPair> generateInputPairs() {
    return Stream.of(
        new InputPair("1234567", false),
        new InputPair("12345678", true),
        new InputPair("1234567891234567", true),
        new InputPair("12345678912345678", false)
    );
  }

  @Override
  protected Supplier<PasswordValidator> validatorSupplier() {
    return () -> new LengthValidator(8, 16);
  }
}
