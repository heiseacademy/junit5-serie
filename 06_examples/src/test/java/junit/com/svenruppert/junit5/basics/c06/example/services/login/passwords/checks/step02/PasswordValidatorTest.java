package junit.com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.step02;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.PasswordCheckResult;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.CaseValidator;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.DigitValidator;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.PasswordValidator;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.WhitespaceValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PasswordValidatorTest {

  public static Stream<Arguments> generateTestDataAll() {
    return Stream.of(
        Arguments.of((Supplier<PasswordValidator>) WhitespaceValidator::new, "Hello World", false),
        Arguments.of((Supplier<PasswordValidator>) WhitespaceValidator::new, "HelloWorld", true),

        Arguments.of((Supplier<PasswordValidator>) DigitValidator::new, "aaaaa2aa", true),
        Arguments.of((Supplier<PasswordValidator>) DigitValidator::new, "aaaaaa", false),

        Arguments.of((Supplier<PasswordValidator>) CaseValidator::new, "HelloWorld", true),
        Arguments.of((Supplier<PasswordValidator>) CaseValidator::new, "helloworld", false)
    );
  }

  public static Stream<Arguments> generateTestDataCombined() {
    Stream.Builder<Arguments> builder = Stream.builder();
    addCaseValidatorTestData(builder);
    addWhitespaceValidatorTestData(builder);
    addDigitValidatorTestData(builder);
    return builder.build();
  }

  private static Stream.Builder<Arguments> addWhitespaceValidatorTestData(Stream.Builder<Arguments> builder) {
    return
        builder
            .add(Arguments.of((Supplier<PasswordValidator>) WhitespaceValidator::new, "Hello World", false))
            .add(Arguments.of((Supplier<PasswordValidator>) WhitespaceValidator::new, "HelloWorld", true));

  }

  private static Stream.Builder<Arguments> addDigitValidatorTestData(Stream.Builder<Arguments> builder) {
    return
        builder
            .add(Arguments.of((Supplier<PasswordValidator>) DigitValidator::new, "aaaaa2aa", true))
            .add(Arguments.of((Supplier<PasswordValidator>) DigitValidator::new, "aaaaaaa", false));
  }

  private static Stream.Builder<Arguments> addCaseValidatorTestData(Stream.Builder<Arguments> builder) {
    return
        builder
            .add(Arguments.of((Supplier<PasswordValidator>) CaseValidator::new, "HelloWorld", true))
            .add(Arguments.of((Supplier<PasswordValidator>) CaseValidator::new, "helloworld", false)
            );
  }


  @ParameterizedTest
//  @MethodSource("generateTestDataAll")
  @MethodSource("generateTestDataCombined")
  void testValidators(Supplier<PasswordValidator> validatorSupplier,
                      String input,
                      Boolean expected) {
    PasswordValidator validator = validatorSupplier.get();
    PasswordCheckResult checkResult = validator.isValid(input);
    assertNotNull(checkResult);
    assertEquals(expected, checkResult.ok());
  }


}
