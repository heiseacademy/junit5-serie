package com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks;

import com.svenruppert.dependencies.core.logger.HasLogger;

public class DigitValidator extends AbstractPasswordvalidator implements HasLogger {

  @Override
  protected boolean isValidImpl(String password) {
    return password.chars().anyMatch(Character::isDigit);
  }

  public String getErrorMessage() {
    return "Password must contain at least one digit.";
  }

}
