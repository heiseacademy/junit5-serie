package junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02;

import junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.VaadinFlowAppExtension.VaadinFlowTest;
import junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.WebdriverResolver.UseWebDriver;
import junit.com.svenruppert.junit5.basics.c09.example.webapp.selenium.MainViewSeleniumTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.svenruppert.junit5.basics.c09.example.webapp.MainView.*;
import static junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.WebdriverResolver.WebBrowser.CHROME;
import static junit.com.svenruppert.junit5.basics.c09.example.webapp.demo02.extensions.WebdriverResolver.WebBrowser.FIREFOX;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UseWebDriver(CHROME)
@VaadinFlowTest(target = "http://localhost", port=8899, elementID=ID_USERNAME)
class AdvancedVaadinFlowTest {

//  @BeforeEach
//  void setUp() throws InterruptedException {
//    Thread.sleep(5_000);
//  }
//
//  @AfterEach
//  void tearDown() throws InterruptedException {
//    Thread.sleep(5_000);
//  }

  @Disabled
  @Test
  void testLogin(FlowSelectorByID selector) {
    assertNotNull(selector);

    //hole die benötigten Komponenten
    UIElements uiElements = uiElements(selector);

    //logischen Tests
    uiElements.setUserNameText("testuser");
    uiElements.setPasswordText("secret");
    uiElements.loginButton().click();

    WebElement navigationButton = selector.byID(ID_NAVIGATE_BUTTON);
    assertTrue(navigationButton.isDisplayed(), "Navigation button should be displayed");
  }


  public UIElements uiElements(FlowSelectorByID selector){
    WebElement usernameField = selector.idTextField(ID_USERNAME);
    WebElement passwordField = selector.idPasswordField(ID_PASSWORD);
    WebElement loginButton = selector.byID(ID_LOGIN_BUTTON);
    return new UIElements(usernameField, passwordField, loginButton);
  }



  public record UIElements(WebElement usernameField,
                           WebElement passwordField,
                           WebElement loginButton) {

    public void setUserNameText(String username) {
      usernameField.click();
      usernameField.sendKeys(username);
    }

    public void setPasswordText(String password) {
      passwordField.click();
      passwordField.sendKeys(password);
    }
  }


}
