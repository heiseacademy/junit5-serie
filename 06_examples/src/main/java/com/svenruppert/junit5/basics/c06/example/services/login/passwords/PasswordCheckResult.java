package com.svenruppert.junit5.basics.c06.example.services.login.passwords;

import com.svenruppert.junit5.basics.c06.example.services.login.passwords.checks.PasswordValidator;

public record PasswordCheckResult(boolean ok, String message, Class<? extends PasswordValidator> ruleName) {

}
