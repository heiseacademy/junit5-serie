package com.svenruppert.junit5.basics.c03.example;

public class GreetService {
  public String processData(String requestValue) {
    String result;
    if(requestValue.length() > 5){
      result  = requestValue.toUpperCase();
    } else {
      result  = requestValue.toLowerCase();
    }
    result = result.replaceAll(" ", "_");
    return "Hello " + result + "!";
  }
}
