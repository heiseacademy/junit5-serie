package junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step03.tests;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.CaseValidator;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.PasswordValidator;
import junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step03.AbstractPasswordValidatorTest;

import java.util.function.Supplier;
import java.util.stream.Stream;


public class CaseValidatorTests
    extends AbstractPasswordValidatorTest {

  @Override
  protected Stream<InputPair> generateInputPairs() {
    return Stream.of(
        new InputPair("helloWorld", true),
        new InputPair("helloworld", false)
    );
  }

  @Override
  protected Supplier<PasswordValidator> validatorSupplier() {
    return CaseValidator::new;
  }
}
