package com.svenruppert.junit5.basics.c06.example.services.auth;

import com.svenruppert.junit5.basics.c06.example.services.login.LoginResult;
import com.svenruppert.junit5.basics.c06.example.services.login.LoginService;
import com.svenruppert.junit5.basics.c06.example.services.user.User;
import com.svenruppert.junit5.basics.c06.example.services.user.UserService;


public class AuthService {
  private final LoginService loginService;
  private final UserService userService;

  public AuthService(LoginService loginService,
                     UserService userService) {
    this.loginService = loginService;
    this.userService = userService;
  }

  public User authenticateUser(String username, String password) {
    LoginResult authenticate = loginService.authenticate(username, password);
    return userService.userByLogin(authenticate.login());
  }

}
