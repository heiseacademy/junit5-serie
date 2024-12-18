package com.svenruppert.junit5.basics.c08.example.restservice;

public class GreetService {
  public String processData(String requestValue) {
    System.out.println("requestValue = " + requestValue);
    String result;
    if (requestValue.length() > 5) {
      result = requestValue.toUpperCase();
    } else {
      result = requestValue.toLowerCase();
    }
    result = result.replaceAll(" ", "_");
    return "Hello " + result + "!";
  }
}
