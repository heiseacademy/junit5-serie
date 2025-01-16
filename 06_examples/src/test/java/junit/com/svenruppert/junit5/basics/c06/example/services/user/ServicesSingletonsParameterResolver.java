package junit.com.svenruppert.junit5.basics.c06.example.services.user;

import com.svenruppert.junit5.basics.c06.example.services.auth.AuthService;
import com.svenruppert.junit5.basics.c06.example.services.login.LoginRepository;
import com.svenruppert.junit5.basics.c06.example.services.login.LoginService;
import com.svenruppert.junit5.basics.c06.example.services.login.passwords.SaltGenerator;
import com.svenruppert.junit5.basics.c06.example.services.user.UserRepository;
import com.svenruppert.junit5.basics.c06.example.services.user.UserService;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ServicesSingletonsParameterResolver implements ParameterResolver {

  private static volatile LoginRepository loginRepository;
  private static volatile LoginService loginService;
  private static volatile UserRepository userRepository;
  private static volatile UserService userService;
  private static volatile AuthService authService;

  private static LoginRepository getOrCreateLoginRepository() {
    if (loginRepository == null) {
      synchronized (ServicesSingletonsParameterResolver.class) {
        if (loginRepository == null) {
          loginRepository = new LoginRepository();
        }
      }
    }
    return loginRepository;
  }

  private static LoginService getOrCreateLoginService() {
    if (loginService == null) {
      synchronized (ServicesSingletonsParameterResolver.class) {
        if (loginService == null) {
          loginService = new LoginService(getOrCreateLoginRepository(), new SaltGenerator());
        }
      }
    }
    return loginService;
  }

  private static UserRepository getOrCreateUserRepository() {
    if (userRepository == null) {
      synchronized (ServicesSingletonsParameterResolver.class) {
        if (userRepository == null) {
          userRepository = new UserRepository();
        }
      }
    }
    return userRepository;
  }

  private static UserService getOrCreateUserService() {
    if (userService == null) {
      synchronized (ServicesSingletonsParameterResolver.class) {
        if (userService == null) {
          userService = new UserService(
              getOrCreateUserRepository(),
              getOrCreateLoginService()
          );
        }
      }
    }
    return userService;
  }

  private static AuthService getOrCreateAuthService() {
    if (authService == null) {
      synchronized (ServicesSingletonsParameterResolver.class) {
        if (authService == null) {
          authService = new AuthService(
              getOrCreateLoginService(),
              getOrCreateUserService()
          );
        }
      }
    }
    return authService;
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
      throws ParameterResolutionException {
    Class<?> type = parameterContext.getParameter().getType();
    return type == LoginRepository.class ||
        type == LoginService.class ||
        type == AuthService.class ||
        type == UserService.class;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
                                 ExtensionContext extensionContext)
      throws ParameterResolutionException {
    Class<?> type = parameterContext.getParameter().getType();

    if (type == LoginRepository.class) {
      return getOrCreateLoginRepository();
    }
    if (type == LoginService.class) {
      return getOrCreateLoginService();
    }
    if (type == UserRepository.class) {
      return getOrCreateUserRepository();
    }
    if (type == UserService.class) {
      return getOrCreateUserService();
    }
    if (type == AuthService.class) {
      return getOrCreateAuthService();
    }
    throw new IllegalArgumentException("Unsupported parameter type: " + type);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.PARAMETER, ElementType.TYPE})
  @ExtendWith(ServicesSingletonsParameterResolver.class)
  public @interface SingletonService {
  }
}
