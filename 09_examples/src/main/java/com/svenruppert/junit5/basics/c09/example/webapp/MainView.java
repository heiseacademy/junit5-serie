package com.svenruppert.junit5.basics.c09.example.webapp;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView
    extends Composite<VerticalLayout>
    implements HasLogger {

  public static final String ID_USERNAME = "username";
  public static final String ID_PASSWORD = "password";
  public static final String ID_LOGIN_BUTTON = "loginButton";
  public static final String ID_NAVIGATE_BUTTON = "navigateButton";


  private final TextField usernameField;
  private final PasswordField passwordField;
  private final Button loginButton;

  private final Button navigateButton;

  public MainView() {
    // Login-Formular
    usernameField = new TextField("Username");
    usernameField.setId(ID_USERNAME);

    passwordField = new PasswordField("Password");
    passwordField.setId(ID_PASSWORD);

    loginButton = new Button("Login", this::onComponentEvent);
    loginButton.setId(ID_LOGIN_BUTTON);

    navigateButton = new Button(
        "Dann mal los mit der Arbeit...",
        event -> {
          logger().info("Navigation Button pressed..");
          Notification notification = new Notification();
          notification.add("Here we are now logged in");
          notification.open();
        });

    navigateButton.setId(ID_NAVIGATE_BUTTON);
    navigateButton.setVisible(false); // Am Anfang verborgen

    getContent()
        .add(usernameField, passwordField, loginButton, navigateButton);
  }

  private void handleLogin() {
    // Einfache "Login"-Logik zur Demonstration
    String user = usernameField.getValue();
    String pass = passwordField.getValue();

    // Für das Testbeispiel gehen wir davon aus, dass "testuser" und "secret"
    // die "richtigen" Zugangsdaten sind.
    if ("testuser".equals(user) && "secret".equals(pass)) {
      // Login erfolgreich
      logger().info("Logged in");
      navigateButton.setVisible(true);
    } else {
      logger().info("Login failed");
      // Login fehlgeschlagen - In einem echten Szenario würde man hier
      // ggf. eine Fehlermeldung anzeigen
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


  public Button getNavigateButton() {
    return navigateButton;
  }

  private void onComponentEvent(ClickEvent<Button> event) {
    handleLogin();
  }
}


