package junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced;

import junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced.extensions.VaadinFlowAppExtension.VaadinFlowTest;
import junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced.extensions.WebdriverResolver.UseWebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.svenruppert.junit5.basics.c09.example.webapp.MainView.*;
import static junit.com.svenruppert.junit5.basics.c09.example.webapp.advanced.extensions.WebdriverResolver.WebBrowser.CHROME;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@UseWebDriver(CHROME)
@VaadinFlowTest(target = "http://localhost", port = 8899, elementID = ID_USERNAME)
class AdvancedVaadinFlowTest {


  @BeforeEach
  void setUp() throws InterruptedException {
    Thread.sleep(5_000);
  }

  @AfterEach
  void tearDown() throws InterruptedException {
    Thread.sleep(5_000);
  }

  @Test
  void name(FlowSelectorByID selector) {
    assertNotNull(selector);

    UIElements uiElements = uiElements(selector);
    uiElements.setUserNameText("testuser");
    uiElements.setPasswordText("secret");
    uiElements.loginButton.click();

    // Überprüfen, ob der Navigationsbutton sichtbar ist
    WebElement navigateButton = selector.idButton(ID_NAVIGATE_BUTTON);
    assertTrue(navigateButton.isDisplayed(),
        "Der Navigationsbutton sollte nach erfolgreichem Login sichtbar sein.");
  }

  public UIElements uiElements(FlowSelectorByID selector) {
    WebElement usernameField = selector.idTextField(ID_USERNAME);
    WebElement passwordField = selector.idPasswordField(ID_PASSWORD);
    WebElement loginButton = selector.byID(ID_LOGIN_BUTTON);
    return new UIElements(usernameField, passwordField, loginButton);
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
}
