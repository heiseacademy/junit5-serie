package com.svenruppert.junit5.basics.c09.example.webapp;

import com.svenruppert.vaadin.nano.CoreUIServiceJava;
import org.apache.commons.cli.ParseException;

public class AppStarter extends CoreUIServiceJava {


  public static void main(String[] args) throws ParseException {
    //https://github.com/svenruppert/nano-vaadin-undertow
    CoreUIServiceJava.main(args);
  }


}
