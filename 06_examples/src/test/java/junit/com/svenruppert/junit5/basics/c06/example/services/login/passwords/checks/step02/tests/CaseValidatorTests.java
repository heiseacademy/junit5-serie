package junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step02.tests;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.CaseValidator;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.PasswordValidator;
import junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step02.AbstractPasswordValidatorTest;

import java.util.function.Supplier;
import java.util.stream.Stream;


public class CaseValidatorTests extends AbstractPasswordValidatorTest {

  protected Stream<InputPair> generateInputPairs() {
    return Stream.of(
        new InputPair("helloWorld", true),
        new InputPair("helloworld", false)
    );
  }

  protected Supplier<PasswordValidator> validatorSupplier() {
    return CaseValidator::new;
  }

}
