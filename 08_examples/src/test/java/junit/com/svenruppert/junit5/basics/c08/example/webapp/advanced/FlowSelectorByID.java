package junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlowSelectorByID {

  private WebDriver driver;

  public FlowSelectorByID(WebDriver driver) {
    this.driver = driver;
  }

  public WebElement byID(String id) {
    return driver.findElement(By.id(id));
  }

  public WebElement idTextField(String id) {
    return byID(id).findElement(By.cssSelector("[id*='input-vaadin-text-field']"));
  }

  public WebElement idPasswordField(String id) {
    return byID(id).findElement(By.cssSelector("[id*='input-vaadin-password-field']"));
  }
}
