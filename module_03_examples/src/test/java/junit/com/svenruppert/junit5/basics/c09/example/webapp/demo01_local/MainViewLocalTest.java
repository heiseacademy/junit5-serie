package junit.com.svenruppert.junit5.basics.c09.example.webapp.demo01_local;

import com.svenruppert.junit5.basics.c09.example.webapp.MainView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainViewLocalTest {

  @Test
  void testSuccessfulLogin() {
    // Erzeuge eine neue Instanz der MainView
    MainView mainView = new MainView();

    // Benutzername und Passwort simulieren
    mainView.getUsernameField().setValue("testuser");
    mainView.getPasswordField().setValue("secret");

    // Den Login-Button "klicken", hier einfach den Listener aufrufen
    mainView.getLoginButton().click();

    // Ebenso sollte der Navigationsbutton sichtbar sein
    Assertions.assertTrue(mainView.getNavigateButton().isVisible(),
        "Nach erfolgreichem Login sollte der Navigate-Button sichtbar sein");
  }

  @Test
  void testFailedLogin() {
    MainView mainView = new MainView();

    // Falsche Credentials
    mainView.getUsernameField().setValue("wronguser");
    mainView.getPasswordField().setValue("wrongpass");

    mainView.getLoginButton().click();

    // Navigationsbutton ebenso unsichtbar
    Assertions.assertFalse(mainView.getNavigateButton().isVisible(),
        "Bei falschen Logindaten bleibt der Navigate-Button unsichtbar");
  }

  @Test
  void testNavigationSimulation() {
    // Normalerweise navigiert Vaadin über den UI-Kontext.
    // Da wir hier keinen Live-UI-Kontext haben, können wir lediglich prüfen,
    // ob der Button korrekt konfiguriert ist.

    MainView mainView = new MainView();

    mainView.getUsernameField().setValue("testuser");
    mainView.getPasswordField().setValue("secret");
    mainView.getLoginButton().click();

    // Button sollte jetzt sichtbar sein und - in einem echten UI - beim Klick zur nächsten Seite navigieren
    Assertions.assertTrue(mainView.getNavigateButton().isVisible());

    // Hier könnte man prüfen, ob der ClickListener des Buttons korrekt gesetzt wurde.
    // Ohne echten UI-Kontext können wir die Navigation selbst nicht durchführen.
    // Aber wir können zumindest überprüfen, ob der Button wie erwartet initialisiert ist.
    Assertions.assertNotNull(mainView.getNavigateButton().getUI(),
        "In einem vollwertigen UI-Kontext wäre hier die UI verfügbar.");
  }
}
