package com.svenruppert.junit5.basics.c06.example.services.user;

public record User(int uid, String forename, String surname) {
  public User() {
    this(-1, "anonymous", "anonymous");
  }
}
