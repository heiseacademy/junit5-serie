package junit.com.svenruppert.junit5.basics.c09.example.webapp.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.helger.commons.mock.CommonsAssert.assertEquals;
import static com.svenruppert.junit5.basics.c09.example.webapp.MainView.*;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainViewSeleniumTest {

  private static final String URL = "http://localhost:9999";
  private static WebDriver driver;
  private FlowSelectorByID selector;

  @BeforeAll
  static void setup() {
//    WebDriverManager.chromedriver().setup();
    WebDriverManager.firefoxdriver().setup();
  }

  @AfterAll
  static void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @BeforeEach
  void beforeEach() {
//    driver = new ChromeDriver();
    driver = new FirefoxDriver();
    selector = new FlowSelectorByID(driver);
    driver.manage().window().maximize();
    driver.get(URL);
    new WebDriverWait(driver, ofSeconds(10), ofSeconds(10))
        .until(titleIs("Hello World"));
    new WebDriverWait(driver, ofSeconds(10), ofSeconds(1))
        .until(visibilityOfElementLocated(By.id(ID_USERNAME)));
  }

  @AfterEach
  void afterEach() {
    driver.quit();
  }


  public record UIElements(WebElement usernameField,
                           WebElement passwordField,
                           WebElement loginButton) {
    public UIElements setUserNameText(String txt){
      usernameField.click();
      usernameField.sendKeys(txt);
      return this;
    }

    public UIElements setPasswordText(String txt){
      passwordField.click();
      passwordField.sendKeys(txt);
      return this;
    }
    public UIElements clickButton(){
      loginButton.click();
      return this;
    }
  }



  @Test
  @Disabled
  void testSuccessfulLogin() {
    String currentUrl = driver.getCurrentUrl();
    assertEquals("http://localhost:9999/", currentUrl);
    String title = driver.getTitle();
    assertEquals("Hello World", title);
    String pageSource = driver.getPageSource();
    assertTrue(pageSource.contains("Hello World"));

    // Felder und Button per ID finden und befüllen
    //WebElement usernameField = driver.findElement(By.id(ID_USERNAME));
    //WebElement element = driver.findElement(By.cssSelector("[id*='input-vaadin-text-field']"));

    //WebElement usernameField = selector.idTextField(ID_USERNAME);
    //WebElement passwordField = selector.idPasswordField(ID_PASSWORD);
    //WebElement loginButton = selector.byID(ID_LOGIN_BUTTON);

//    uiElements().usernameField.click();
//    uiElements().usernameField.sendKeys("testuser");

//    uiElements().passwordField.click();
//    uiElements().passwordField.sendKeys("secret");

    UIElements uiElements = uiElements();
    uiElements.setUserNameText("testuser");
    uiElements.setPasswordText("secret");
    uiElements.clickButton();

    // Überprüfen, ob der Navigationsbutton sichtbar ist
    WebElement navigateButton = selector.byID(ID_NAVIGATE_BUTTON);
    assertTrue(navigateButton.isDisplayed(),
        "Der Navigationsbutton sollte nach erfolgreichem Login sichtbar sein.");
  }

  @Test
  @Disabled
  void testFailedLogin() {
    uiElements()
        .setUserNameText("wronguser")
        .setPasswordText("wrongpass")
        .clickButton();

    // Der Navigationsbutton sollte ebenfalls nicht sichtbar sein
    WebElement navigateButton = selector.byID(ID_NAVIGATE_BUTTON);
    Assertions.assertFalse(navigateButton.isDisplayed(),
        "Der Navigationsbutton sollte bei falschen Logindaten unsichtbar bleiben.");
  }

  public UIElements uiElements(){
    WebElement usernameField = selector.idTextField(ID_USERNAME);
    WebElement passwordField = selector.idPasswordField(ID_PASSWORD);
    WebElement loginButton = selector.byID(ID_LOGIN_BUTTON);
    return new UIElements(usernameField, passwordField, loginButton);
  }

  public static class FlowSelectorByID {

    private final WebDriver driver;

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


}
