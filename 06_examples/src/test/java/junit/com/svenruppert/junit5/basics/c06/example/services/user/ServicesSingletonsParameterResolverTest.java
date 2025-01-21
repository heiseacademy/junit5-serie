package junit.com.svenruppert.junit5.basics.c06.example.services.user;

import com.svenruppert.junit5.basics.c06.example.services.CreateEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.login.Login;
import com.svenruppert.junit5.basics.c06.example.services.login.LoginRepository;
import com.svenruppert.junit5.basics.c06.example.services.user.User;
import com.svenruppert.junit5.basics.c06.example.services.user.UserService;
import org.junit.jupiter.api.Test;

import static junit.com.svenruppert.junit5.basics.c06.example.services.user.ServicesSingletonsParameterResolver.*;
import static junit.com.svenruppert.junit5.basics.c06.example.services.user.ServicesSingletonsParameterResolver.getOrCreateUserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServicesSingletonsParameterResolverTest {


  @Test
  void test001() {
    LoginRepository loginRepository = getOrCreateLoginRepository();

    UserService userService = getOrCreateUserService();
    CreateEntityResponse<User> user = userService.createUser(
        "max.mustermann",
        "secure",
        "Max",
        "Mustermann"
    );
    Login login = loginRepository.userLoginByUID(user.entity().uid());
    assertNotNull(login);
  }
  @Test
  void test002() {
    UserService userService = getOrCreateUserService();
    CreateEntityResponse<User> user = userService.createUser(
        "max.mustermann",
        "secure",
        "Max",
        "Mustermann"
    );
    LoginRepository loginRepository = getOrCreateLoginRepository();
    Login login = loginRepository.userLoginByUID(user.entity().uid());
    assertNotNull(login);
  }
}
