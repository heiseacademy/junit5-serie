package junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step03.tests;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.DigitValidator;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.PasswordValidator;
import junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step03.AbstractPasswordValidatorTest;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class DigitValidatorTest
    extends AbstractPasswordValidatorTest {

  @Override
  protected Stream<InputPair> generateInputPairs() {
    return Stream.of(
        new InputPair("hello3World", true),
        new InputPair("helloWorld", false)
    );
  }

  @Override
  protected Supplier<PasswordValidator> validatorSupplier() {
    return DigitValidator::new;
  }
}
