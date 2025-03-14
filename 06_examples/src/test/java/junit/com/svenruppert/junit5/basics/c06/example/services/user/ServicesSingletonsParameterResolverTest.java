package junit.com.svenruppert.junit5.basics.c06.example.services.user;

import com.svenruppert.junit5.basics.c06.example.services.CreateEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.login.Login;
import com.svenruppert.junit5.basics.c06.example.services.login.LoginRepository;
import com.svenruppert.junit5.basics.c06.example.services.user.User;
import com.svenruppert.junit5.basics.c06.example.services.user.UserService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static junit.com.svenruppert.junit5.basics.c06.example.services.user.ServicesSingletonsParameterResolver.getOrCreateLoginRepository;
import static junit.com.svenruppert.junit5.basics.c06.example.services.user.ServicesSingletonsParameterResolver.getOrCreateUserService;
import static org.junit.jupiter.api.Assertions.*;

public class ServicesSingletonsParameterResolverTest {

  @NotNull
  private static User checkMaxMustermann(CreateEntityResponse<User> userResponse) {
    assertNotNull(userResponse);
    assertTrue(userResponse.created());
    User user = userResponse.entity();
    assertNotNull(user);
    assertEquals("Max", user.forename());
    assertEquals("Mustermann", user.surname());
    assertNotEquals(-1, user.uid());
    return user;
  }

  @AfterEach
  void afterEach() {
    ServicesSingletonsParameterResolver.clearRepos();
  }

  @Test
  void test001() {
    LoginRepository loginRepository = getOrCreateLoginRepository();
    UserService userService = getOrCreateUserService();
    CreateEntityResponse<User> userResponse = userService.createUser(
        "max.mustermann",
        "SecureMe",
        "Max",
        "Mustermann");

    User user = checkMaxMustermann(userResponse);

    Login login = loginRepository.userLoginByUID(user.uid());
    assertNotNull(login);
  }

  @Test
  void test002() {
    UserService userService = getOrCreateUserService();
    CreateEntityResponse<User> userResponse = userService.createUser(
        "max.mustermann",
        "SecureMe",
        "Max",
        "Mustermann");

    User user = checkMaxMustermann(userResponse);

    LoginRepository loginRepository = getOrCreateLoginRepository();
    Login login = loginRepository.userLoginByUID(user.uid());
    assertNotNull(login);
  }
}
