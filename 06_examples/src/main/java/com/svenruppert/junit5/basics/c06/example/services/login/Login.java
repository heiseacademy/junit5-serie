package com.svenruppert.junit5.basics.c06.example.services.login;

public record Login(String loginName, String passwordHash, String salt, int uid) {
  public Login() {
    this("anonymous", "anonymous", "anonymous", -1);
  }
}
