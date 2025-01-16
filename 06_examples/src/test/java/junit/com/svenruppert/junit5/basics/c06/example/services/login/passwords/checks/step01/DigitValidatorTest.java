package junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step01;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.PasswordCheckResult;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.DigitValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DigitValidatorTest {

  @ParameterizedTest
  @CsvSource({
      "aaaaa, false",
      "aaa2aa , true"
  })
  void test001(String input, Boolean expected) {
    DigitValidator validator = new DigitValidator();
    PasswordCheckResult checkResult = validator.isValid(input);
    assertNotNull(checkResult);
    assertEquals(expected, checkResult.ok());
  }
}