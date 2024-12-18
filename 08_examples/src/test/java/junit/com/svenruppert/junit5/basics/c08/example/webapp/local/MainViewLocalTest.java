package junit.com.svenruppert.junit5.basics.c08.example.webapp.local;

import com.svenruppert.junit5.basics.c08.example.webapp.MainView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class MainViewLocalTest {

  @Test
  @Disabled
  void testSuccessfulLogin() {
    // Erzeuge eine neue Instanz der MainView
    MainView mainView = new MainView();

    // Benutzername und Passwort simulieren
    mainView.getUsernameField().setValue("testuser");
    mainView.getPasswordField().setValue("secret");

    // Den Login-Button "klicken", hier einfach den Listener aufrufen
    mainView.getLoginButton().click();

    // Nun prüfen, ob die Willkommensnachricht sichtbar ist
    Assertions.assertTrue(mainView.getWelcomeMessage().isVisible(),
        "Nach korrektem Login sollte die Willkommensnachricht sichtbar sein");

    // Ebenso sollte der Navigationsbutton sichtbar sein
    Assertions.assertTrue(mainView.getNavigateButton().isVisible(),
        "Nach erfolgreichem Login sollte der Navigate-Button sichtbar sein");
  }

  @Test
  @Disabled
  void testFailedLogin() {
    MainView mainView = new MainView();

    // Falsche Credentials
    mainView.getUsernameField().setValue("wronguser");
    mainView.getPasswordField().setValue("wrongpass");

    mainView.getLoginButton().click();

    // Willkommensnachricht sollte unsichtbar bleiben
    Assertions.assertFalse(mainView.getWelcomeMessage().isVisible(),
        "Bei falschen Logindaten bleibt die Willkommensnachricht unsichtbar");

    // Navigationsbutton ebenso unsichtbar
    Assertions.assertFalse(mainView.getNavigateButton().isVisible(),
        "Bei falschen Logindaten bleibt der Navigate-Button unsichtbar");
  }

  @Test
  @Disabled
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
