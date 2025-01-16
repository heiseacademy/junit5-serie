package junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step01;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.PasswordCheckResult;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.CommonPasswordValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CommonPasswordValidatorTest {


  @ParameterizedTest
  @CsvSource({
      "password , false",
      "12345678 , false",
      "Hz&tGfR , true"
  })
  void test001(String input, Boolean expected) {
    CommonPasswordValidator validator = new CommonPasswordValidator();
    PasswordCheckResult checkResult = validator.isValid(input);
    assertNotNull(checkResult);
    assertEquals(expected, checkResult.ok());
  }
}