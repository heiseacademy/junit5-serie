package com.svenruppert.junit5.basics.c06.example.services.login;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit5.basics.c06.example.services.CreateEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.DeleteEntityResponse;
import com.svenruppert.junit5.basics.c06.example.services.UpdateEntityResponse;

import java.util.Map;

import static java.util.HashMap.newHashMap;

public class LoginRepository implements HasLogger {

  public static final Login ANONYMOUS_LOGIN = new Login();
  private static final GlobalUIDGenerator GLOBAL_UID_GENERATOR = GlobalUIDGenerator.getInstance();
  private static final Map<String, Login> USER_LOGIN_REPO_LOGIN_NAME = newHashMap(100);
  private static final Map<Integer, Login> USER_LOGIN_REPO_UID = newHashMap(100);


  public void clearRepository() {
    USER_LOGIN_REPO_LOGIN_NAME.clear();
    USER_LOGIN_REPO_UID.clear();
  }


  public Login userLoginByUID(int uid) {
    return USER_LOGIN_REPO_UID.getOrDefault(uid, ANONYMOUS_LOGIN);
  }

  public Login userLoginByLoginName(String loginName) {
    return USER_LOGIN_REPO_LOGIN_NAME.getOrDefault(loginName, ANONYMOUS_LOGIN);
  }

  public DeleteEntityResponse<Login> deleteLogin(Login login) {
    logger().warn("deleting login {}", login);
    USER_LOGIN_REPO_LOGIN_NAME.remove(login.loginName());
    USER_LOGIN_REPO_UID.remove(login.uid());
    return new DeleteEntityResponse<>(true, "Login " + login.loginName() + " deleted", login);
  }

  public CreateEntityResponse<Login> createLogin(Login login) {
    return createLogin(login.loginName(), login.passwordHash(), login.salt());
  }

  public CreateEntityResponse<Login> createLogin(String loginName,
                                                 String passwordHash,
                                                 String salt) {
    logger().info("creating login {}", loginName);
    if (USER_LOGIN_REPO_LOGIN_NAME.containsKey(loginName)) {
      return new CreateEntityResponse<>(false, "Login " + loginName + " already exists", ANONYMOUS_LOGIN);
    }
    int nextUID = GLOBAL_UID_GENERATOR.getNextUID();
    try {
      Login value = new Login(loginName, passwordHash, salt, nextUID);
      USER_LOGIN_REPO_UID.put(nextUID, value);
      USER_LOGIN_REPO_LOGIN_NAME.put(value.loginName(), value);
      return new CreateEntityResponse<>(true, "Login " + loginName + " created", value);
    } catch (Exception e) {
      USER_LOGIN_REPO_UID.remove(nextUID);
      USER_LOGIN_REPO_LOGIN_NAME.remove(loginName);
      return new CreateEntityResponse<>(false, e.getMessage(), ANONYMOUS_LOGIN);
    }
  }

  public UpdateEntityResponse<Login> changePassword(int uid, String passwordHash, String salt) {
    if (USER_LOGIN_REPO_UID.containsKey(uid)) {
      Login login = USER_LOGIN_REPO_UID.get(uid);
      Login newEntry = new Login(login.loginName(), passwordHash, salt, login.uid());
      USER_LOGIN_REPO_UID.replace(uid, newEntry);
      USER_LOGIN_REPO_LOGIN_NAME.replace(login.loginName(), newEntry);
      return new UpdateEntityResponse<>(true, "Login " + login.loginName() + " updated", newEntry);
    } else {
      logger().warn("trying to update not know user with UID {}", uid);
      return new UpdateEntityResponse<>(false, "trying to update not know user with UID " + uid, ANONYMOUS_LOGIN);
    }
  }

  public UpdateEntityResponse<Login> updateLogin(Login login) {
    logger().info("updating login {}", login);
    if (USER_LOGIN_REPO_LOGIN_NAME.containsKey(login.loginName()) &&
        USER_LOGIN_REPO_UID.containsKey(login.uid())) {
      USER_LOGIN_REPO_LOGIN_NAME.put(login.loginName(), login);
      USER_LOGIN_REPO_UID.put(login.uid(), login);
      return new UpdateEntityResponse<>(true, "Login " + login.uid() + " updated", login);
    } else {
      return new UpdateEntityResponse<>(false, "Login " + login.uid() + " not found / not updated", ANONYMOUS_LOGIN);
    }
  }

  public CreateEntityResponse<Login> storeLogin(Login login) {
    logger().info("storing login {}", login);
    USER_LOGIN_REPO_LOGIN_NAME.put(login.loginName(), login);
    USER_LOGIN_REPO_UID.put(login.uid(), login);
    return new CreateEntityResponse<>(true, "Login " + login.loginName() + " stored", login);
  }

}
