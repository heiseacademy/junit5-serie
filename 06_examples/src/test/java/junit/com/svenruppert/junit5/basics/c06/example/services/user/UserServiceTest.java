package junit.com.svenruppert.junit5.basics.c06.example.services.user;

import com.svenruppert.junit5.basics.c06.example.services.CreateEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.DeleteEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.UpdateEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.login.Login;
import com.svenruppert.junit5.basics.c06.example.services.login.LoginService;
import com.svenruppert.junit5.basics.c06.example.services.user.User;
import com.svenruppert.junit5.basics.c06.example.services.user.UserService;
import junit.com.svenruppert.junit5.basics.c06.example.services.user.ServicesSingletonsParameterResolver.SingletonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.svenruppert.junit5.basics.c06.example.services.user.UserRepository.ANONYMOUS_USER;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

  private static void checkMaxMustermann(User user) {
    assertNotNull(user);
    assertNotEquals(-1, user.uid());
    assertEquals("Max", user.forename());
    assertEquals("Mustermann", user.surname());
  }

  @AfterEach
  void afterEach() {
    ServicesSingletonsParameterResolver.clearRepos();
  }

  @Test
  void test001(
      @SingletonService UserService service,
      @SingletonService LoginService loginService) {

    CreateEntityResponse<User> createResponse = service.createUser(
        "max.mustermann",
        "SecureMe",
        "Max",
        "Mustermann");

    assertNotNull(createResponse);
    User user = createResponse.entity();

    checkMaxMustermann(user);
    checkMaxMustermann(service.userByUserName("max.mustermann"));
    checkMaxMustermann(service.userByUID(user.uid()));

    UpdateEntityResponse<Login> loginUpdate = loginService.changePassword(
        user.uid(),
        "SecureMe",
        "password");
    assertNotNull(loginUpdate);
    assertTrue(loginUpdate.updated());

    DeleteEntityResponse<User> deleteResult = service.deleteUser(user);
    assertNotNull(deleteResult);
    assertTrue(deleteResult.deleted());

    User deletedUser = service.userByUserName("max.mustermann");
    assertNotNull(deletedUser);
    assertEquals(-1, deletedUser.uid());
    assertEquals(ANONYMOUS_USER, deletedUser);

  }


}
