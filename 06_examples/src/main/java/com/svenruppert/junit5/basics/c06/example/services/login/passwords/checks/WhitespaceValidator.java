package com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks;

import com.svenruppert.dependencies.core.logger.HasLogger;

public class WhitespaceValidator extends AbstractPasswordvalidator implements HasLogger {
  protected boolean isValidImpl(String password) {
    return !password.contains(" ");
  }

  public String getErrorMessage() {
    return "Password must not contain spaces.";
  }
}
