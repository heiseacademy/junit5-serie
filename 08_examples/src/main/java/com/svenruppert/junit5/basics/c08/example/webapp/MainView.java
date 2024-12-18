package com.svenruppert.junit5.basics.c08.example.webapp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView
    extends VerticalLayout {

  public static final String ID_USERNAME = "username";
  public static final String ID_PASSWORD = "password";
  public static final String ID_LOGIN_BUTTON = "loginButton";
  public static final String ID_WELCOME_MESSAGE = "welcomeMessage";
  public static final String ID_NAVIGATE_BUTTON = "navigateButton";


  private TextField usernameField;
  private PasswordField passwordField;
  private Button loginButton;
  private Div welcomeMessage;
  private Button navigateButton;

  public MainView() {
    // Login-Formular
    usernameField = new TextField("Username");
    usernameField.setId(ID_USERNAME);

    passwordField = new PasswordField("Password");
    passwordField.setId(ID_PASSWORD);

    loginButton = new Button("Login", event -> handleLogin());
    loginButton.setId(ID_LOGIN_BUTTON);

    // Willkommensnachricht und Navigation erst nach Login sichtbar
    welcomeMessage = new Div("Willkommen!");
    welcomeMessage.setId(ID_WELCOME_MESSAGE);
    welcomeMessage.setVisible(false); // Am Anfang verborgen

    navigateButton = new Button("Weiter zur nächsten Seite", event ->
        navigateButton.getUI().ifPresent(ui -> ui.navigate("second")));
    navigateButton.setId(ID_NAVIGATE_BUTTON);
    navigateButton.setVisible(false); // Am Anfang verborgen

    add(usernameField, passwordField, loginButton, welcomeMessage, navigateButton);
  }

  private void handleLogin() {
    // Einfache "Login"-Logik zur Demonstration
    String user = usernameField.getValue();
    String pass = passwordField.getValue();

    // Für das Testbeispiel gehen wir davon aus, dass "testuser" und "secret"
    // die "richtigen" Zugangsdaten sind.
    if ("testuser".equals(user) && "secret".equals(pass)) {
      // Login erfolgreich
      welcomeMessage.setVisible(true);
      navigateButton.setVisible(true);
    } else {
      // Login fehlgeschlagen - In einem echten Szenario würde man hier ggf. eine Fehlermeldung anzeigen
    }
  }

  public TextField getUsernameField() {
    return usernameField;
  }

  public PasswordField getPasswordField() {
    return passwordField;
  }

  public Button getLoginButton() {
    return loginButton;
  }

  public Div getWelcomeMessage() {
    return welcomeMessage;
  }

  public Button getNavigateButton() {
    return navigateButton;
  }
}


