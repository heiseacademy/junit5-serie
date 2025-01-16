package com.svenruppert.junit5.basics.c06.example.services.login.passwords;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.*;

import java.util.List;
import java.util.stream.Stream;

public class PasswordValidatorService implements HasLogger {

  private Stream<PasswordValidator> ruleChecks = Stream.of(
      new CaseValidator(),
      new CommonPasswordValidator(),
      new DigitValidator(),
      new LengthValidator(8, 16),
//      new PersonalInfoValidator("",""), //TODO
//      new ReusedPasswordValidator(previousPasswords),
      new SpecialCharacterValidator(),
      new WhitespaceValidator()
  );

  public List<PasswordCheckResult> checkeRules(String password) {
    return ruleChecks
        .map(rule -> rule.isValid(password))
        .peek(result -> logger().info("Password check result: {}", result))
        .toList();
  }


}
