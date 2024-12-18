package junit.com.svenruppert.junit5.basics.c08.example.webapp.docker;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@ExtendWith(SelenoidExtension.class)
@ExtendWith(WebDriverExtension.class)
public class VaadinFlowUITest {

  @Test
  @Disabled
  void testLoginFunctionality(WebDriver driver) {
    // Vaadin-Anwendung aufrufen
    driver.get("http://localhost:9999");

    // Beispiel: Einfache Login-Form testen
    WebElement usernameField = driver.findElement(By.id("username"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton   = driver.findElement(By.id("login-button"));

    usernameField.sendKeys("testuser");
    passwordField.sendKeys("secret");
    loginButton.click();

    // Überprüfen, ob nach dem Login ein bestimmtes Element vorhanden ist
    WebElement welcomeMessage = driver.findElement(By.id("welcome-message"));
    Assertions.assertTrue(welcomeMessage.isDisplayed(), "Willkommensnachricht sollte angezeigt werden");
  }

  @Test
  @Disabled
  void testNavigationToAnotherView(WebDriver driver) {
    driver.get("http://localhost:9999");

    WebElement navButton = driver.findElement(By.id("navigate-button"));
    navButton.click();

    // Nach dem Klick sollte sich die Ansicht ändern
    WebElement newViewElement = driver.findElement(By.id("new-view-element"));
    Assertions.assertTrue(newViewElement.isDisplayed(), "Neuer View sollte sichtbar sein");
  }
}
