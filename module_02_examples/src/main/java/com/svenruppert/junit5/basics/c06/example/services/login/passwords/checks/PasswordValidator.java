package com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.PasswordCheckResult;

public interface PasswordValidator {
  PasswordCheckResult isValid(String password);
}
