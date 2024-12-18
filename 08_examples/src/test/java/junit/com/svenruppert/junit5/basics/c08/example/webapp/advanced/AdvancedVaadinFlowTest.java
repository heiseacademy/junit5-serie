package junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced;

import junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced.FlowSelectorByIDResolver.FlowSelector;
import junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced.WebdriverResolver.UseWebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.svenruppert.junit5.basics.c08.example.webapp.MainView.*;
import static java.time.Duration.ofSeconds;
import static junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced.WebdriverResolver.WebBrowser.CHROME;
import static junit.com.svenruppert.junit5.basics.c08.example.webapp.advanced.WebdriverResolver.WebBrowser.FIREFOX;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@UseWebDriver(CHROME)
@FlowSelector
class AdvancedVaadinFlowTest {

  public static final String URL = "http://localhost:9999";

  @BeforeEach
  void setUp(WebDriver driver) {
    driver.manage().window().maximize();
    driver.get(URL);
    new WebDriverWait(driver, ofSeconds(10), ofSeconds(1))
        .until(visibilityOfElementLocated(By.id(ID_USERNAME)));
  }

  @Test
  @Disabled
  void name(FlowSelectorByID selector) {
    assertNotNull(selector);

    uiElements(selector).setUserNameText("testuser");
    uiElements(selector).setPasswordText("secret");
    uiElements(selector).loginButton.click();

    // Überprüfen, ob die Willkommensnachricht sichtbar ist
    WebElement welcomeMessage = selector.byID(ID_WELCOME_MESSAGE);
    assertTrue(welcomeMessage.isDisplayed(),
        "Die Willkommensnachricht sollte nach erfolgreichem Login sichtbar sein.");

    // Überprüfen, ob der Navigationsbutton sichtbar ist
    WebElement navigateButton = selector.byID(ID_NAVIGATE_BUTTON);
    assertTrue(navigateButton.isDisplayed(),
        "Der Navigationsbutton sollte nach erfolgreichem Login sichtbar sein.");
  }

  public record UIElements(WebElement usernameField,
                           WebElement passwordField,
                           WebElement loginButton) {
    public WebElement setUserNameText(String txt) {
      usernameField.click();
      usernameField.sendKeys(txt);
      return usernameField;
    }

    public WebElement setPasswordText(String txt) {
      passwordField.click();
      passwordField.sendKeys(txt);
      return passwordField;
    }
  }
  public UIElements uiElements(FlowSelectorByID selector) {
    WebElement usernameField = selector.idTextField(ID_USERNAME);
    WebElement passwordField = selector.idPasswordField(ID_PASSWORD);
    WebElement loginButton = selector.byID(ID_LOGIN_BUTTON);
    return new UIElements(usernameField, passwordField, loginButton);
  }
}
