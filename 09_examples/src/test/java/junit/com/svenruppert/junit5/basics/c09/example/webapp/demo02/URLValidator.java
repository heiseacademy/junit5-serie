package junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class URLValidator {

  public static boolean isValidHttpOrHttpsURL(String url) {
    try {
      URI uri = URI.create(url);
      URL parsedUrl = uri.toURL();
      String protocol = parsedUrl.getProtocol();
      return "http".equalsIgnoreCase(protocol) || "https".equalsIgnoreCase(protocol);
    } catch (MalformedURLException e) {
      return false;
    }
  }
}
